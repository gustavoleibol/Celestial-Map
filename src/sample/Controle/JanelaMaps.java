package sample.Controle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import sample.Animacoes.Jello;
import sample.Animacoes.SlideInRight;
import sample.Modelo.*;

import java.util.*;


public class JanelaMaps extends ControleBase {

    @FXML
    private Canvas canvas;
    @FXML
    private AnchorPane Maps;
    @FXML
    private Label ponto;
    private ObservableList<Estrela> aux1;
    private List<Integer> aux2;
    ObservableList<Linha> li;
    private ArrayList<Estrela> suporte;
    private ArrayList<Integer> ids;
    private Estrela repetirNAO = null;
    private int cont=0, Rd1, Rd2,esc, RdConst;
    private static final int escala=1;
    private static double raio = 6;

    public void initialize() {
        initial();
    }

    private void initial() {
        ponto.setText("Detected Star: NO");
        aux2 = new ArrayList<Integer>();
        cont = 0;
        repetirNAO = null;
        suporte = new ArrayList<>();
        GraphicsContext gc = canvas.getGraphicsContext2D();
        draw(gc);
        Random rd = new Random();
        esc = rd.nextInt(3)+1;
        esc = esc + 8;
        raio = esc / 2;
        ConstelacaoAleatoria(gc);

        canvas.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                    try {
                    double x = event.getX();
                    double y = event.getY();
                    for (Estrela star : aux1) {
                        double x1 = (star.getX() / escala) + Rd1;
                        double y1 = (star.getY() / escala) + Rd2;
                        double distancia = Math.sqrt(Math.pow(x1 - x, 2) + Math.pow(y1 - y, 2));
                        if(distancia <= 10.0 && cont < 1) {
                            gc.setFill(Color.valueOf(JDBCUsuariosDAO.getInstance().getLogado().getCorEstrela()));
                            gc.fillOval(star.getX() + Rd1, star.getY() + Rd2, esc, esc);
                            cont++;
                            suporte.add(star);
                            repetirNAO = star;
                            ponto.setText("Detected Star: NICE");
                            new Jello(ponto).setSpeed(2).play();
                            break;
                        }
                        if (distancia <= 10.0 && cont == 1 && repetirNAO != star) {
                            cont = 0;
                            drawShapes(gc, (star.getX() / escala) + Rd1, (star.getY() / escala) + Rd2, star);
                            if (li.size() == 0) {
                                Catalogo_has_Constelacao catconst = new Catalogo_has_Constelacao();
                                catconst.setCatalogo(JDBCUsuariosDAO.getInstance().getLogado().getCatalogo());
                                catconst.setConstelacao(JDBCConstelacaoDAO.getInstance().Search(RdConst));

                                int confere = JDBCCatalogo_has_ConstelacaoDAO.getInstance().Confere(RdConst,JDBCUsuariosDAO.getInstance().getLogado().getCatalogo());
                                if(confere == 0) {
                                    JDBCCatalogo_has_ConstelacaoDAO.getInstance().create(catconst);
                                    mensagem(Alert.AlertType.CONFIRMATION, "Congratulations!! You have found the "+catconst.getConstelacao().getNome()+" constellation, it is now in your catalog.");
                                    Restart();
                                }
                                else {
                                    mensagem(Alert.AlertType.INFORMATION, "You already have this constellation!");
                                    Restart();
                                }
                            }
                            break;
                        }else{
                            ponto.setText("Detected Star: NO");
                            new Jello(ponto).setSpeed(2).play();
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    private void ConstelacaoAleatoria(GraphicsContext gc) {
        //pega uma constelacao aleatoria
        ids = JDBCConstelacaoDAO.getInstance().retornaFeitas();
        Collections.shuffle(ids);

        Random rd = new Random();
        RdConst = ids.get(0);
        int contAleatorio = JDBCCatalogo_has_ConstelacaoDAO.getInstance().contadorCatalogo(JDBCUsuariosDAO.getInstance().getLogado().getCatalogo());
        aux1 = JDBCEstrelaDAO.getInstance().estrelas_de_Certa_Constelacao(RdConst);//constelacao
        li = JDBCLinhaDAO.getInstance().verifica(RdConst);//linhas
        Rd1 = rd.nextInt(220) + 1;
        Rd2 = rd.nextInt(220) + 1;
        for (Estrela star : aux1) {
            double x = star.getX() / escala;
            double y = star.getY() / escala;
            gc.setFill(Color.WHITE);
            gc.fillOval(x + Rd1, y + Rd2, esc, esc);
        }

        if(contAleatorio == 88) {
            mensagem(Alert.AlertType.CONFIRMATION, "Congratulations!!! You cataloged all the 88 constellations!");
            Maps.getChildren().clear();
            try {
                AnchorPane pane = FXMLLoader.load(getClass().getResource("../Visao/janelaCatalogo.fxml"));
                Maps.getChildren().add(pane);
                new SlideInRight(Maps).play();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void drawShapes(GraphicsContext gc, double x, double y, Estrela star){//desenha linha
        for (Estrela estrela : suporte) {
            gc.setFill(Color.WHITE);
            gc.setStroke(Color.valueOf(JDBCUsuariosDAO.getInstance().getLogado().getCorLinha()));
            gc.setLineWidth(4);
            double x1 = (estrela.getX() / escala) + Rd1;
            double y1 = (estrela.getY() / escala) + Rd2;
            Linha l=null;

            for (int i = 0; i < li.size(); i++) {
                l = li.get(i);
                if ((estrela.getId() == l.getIdEstrela1() && star.getId() == l.getIdEstrela2()) || (star.getId() == l.getIdEstrela1() && estrela.getId() == l.getIdEstrela2())) {
                    System.out.println("[LINHA CERTA]");
                    ponto.setText("Detected Star: Hit a Line, Keep this");
                    new Jello(ponto).setSpeed(2).play();
                    gc.strokeLine(x1 + raio, y1 + raio, x + raio, y + raio);
                    li.remove(l);
                    break;
                }else if (i == li.size() - 1) {
                    System.out.println("[LINHA ERRADA]");
                    ponto.setText("Detected Star: Failed a Line");
                    new Jello(ponto).setSpeed(2).play();
                    break;
                }
            }
        }

        suporte.clear();
        for (Estrela s : aux1) {//reprita os pontos
            double x1 = s.getX() / escala;
            double y2 = s.getY() / escala;
            gc.setFill(Color.WHITE);
            gc.fillOval(x1 + Rd1, y2 + Rd2, esc, esc);
        }
    }

    private void draw(GraphicsContext gc) {//estrelas fakes
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());

        Random rnd = new Random(System.currentTimeMillis());
        gc.setFill(Color.WHITE);
        for (int i = 0; i < 100; i++) {
            double x = rnd.nextDouble() * 730;
            double y = rnd.nextDouble() * 600;
            double t = rnd.nextDouble() * 7;
            gc.fillOval(x, y, t, t);
        }
    }
    private void Restart(){
        aux1.clear();
        aux2.clear();
        cont = 0;
        repetirNAO = null;
        Rd1=0;
        Rd2=0;
        initial();
        suporte.clear();
    }
}