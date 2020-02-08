package sample.Controle;

import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import sample.Animacoes.Flash;
import sample.Modelo.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

public class JanelaDesenharConstelacao extends ControleBase {

    @FXML
    private Canvas canvas;
    @FXML
    private ComboBox<Constelacao> cb;
    @FXML
    private CheckBox btLinha;
    @FXML
    private Label Draw;
    private Estrela repetirNAO = null;
    private ArrayList<Estrela> posicao;
    private ArrayList<Linha> linha;
    private GraphicsContext gc;
    private ArrayList<Estrela> suporte;
    private int ultimaPosicao = 0;
    private int cont=0;

    public void initialize() {
        new Flash(Draw).setDelay(Duration.millis(500)).play();
        suporte = new ArrayList<>();
        linha = new ArrayList<>();
        ultimaPosicao = JDBCEstrelaDAO.getInstance().list();//ultima posicao estrelas
        gc = canvas.getGraphicsContext2D();
        posicao = new ArrayList<>();//ira armazena os pontos
        cb.setItems(JDBCConstelacaoDAO.getInstance().List2());
        //DESENHANDO AS ESTRELAS
        if(btLinha.isSelected() == false) {
                canvas.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent Event) {
                        ultimaPosicao = ultimaPosicao + 1;
                        System.out.println("last: "+ultimaPosicao);
                        double x = Event.getX();
                        double y = Event.getY();
                        Estrela estrela = new Estrela();
                        estrela.setId(ultimaPosicao);
                        estrela.setIdConstelacao(cb.getValue().getId());
                        estrela.setX(x);
                        estrela.setY(y);

                        posicao.add(estrela);

                        gc.setFill(Color.WHITE);
                        gc.fillOval(x, y, 12, 12);
                    }
                });
        }
    }

    @FXML
    public void Linha(){
        canvas.getGraphicsContext2D().clearRect(0, 0,532,345);
        for(Estrela star: posicao){//repritrando as linhas
            double x = star.getX();
            double y = star.getY();
            gc.setFill(Color.WHITE);
            gc.fillOval(x, y, 12, 12);
        }
        if (btLinha.isSelected() == true) {
            canvas.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    double x = event.getX();
                    double y = event.getY();
                    for (Estrela star : posicao) {//percorre todas as estrelas da constelacao tal
                        double distancia = DistanciaEuclidiana(star.getX(), x, star.getY(),y);
                        if (distancia <= 12.0 && cont < 1){
                            cont++;
                            System.out.println("achou 1");
                            suporte.add(star);
                            repetirNAO=star;
                            break;
                        }
                        if(distancia<=12.0 && cont == 1 && repetirNAO!=star){
                            cont = 0;
                            System.out.println("achou 2");
                            System.out.println("surgiu desenho");
                            GraphicsContext gc  = canvas.getGraphicsContext2D();
                            drawShapes(gc, star);
                            break;
                        }
                    }
                }
            });
        }
        else{
            canvas.getGraphicsContext2D().clearRect(0, 0,532,345);
            suporte.clear();
            posicao.clear();
            btLinha.setSelected(false);
            initialize();
        }
    }

    private void drawShapes(GraphicsContext gc, Estrela star){
        try {
            Linha l = new Linha();
            for (Estrela estrela : suporte) {
                l.setIdConstelacao(estrela.getIdConstelacao());
                l.setIdEstrela1(estrela.getId());
                l.setIdEstrela2(star.getId());

                linha.add(l);

                gc.setFill(Color.RED);
                gc.setStroke(Color.YELLOW);
                gc.setLineWidth(5);
                gc.strokeLine(estrela.getX() + 6, estrela.getY() + 6, star.getX() + 6, star.getY() + 6);
            }

            for (Estrela s : posicao) {//reprita os pontos
                double x = s.getX();
                double y = s.getY();
                gc.setFill(Color.WHITE);
                gc.fillOval(x, y, 12, 12);
            }
            suporte.clear();//limpa o suporte para poder utilizar array novamente
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public ArrayList<Estrela> processResult() { return posicao; }

    public double DistanciaEuclidiana(double x1, double x2, double y1, double y2){
        double distancia = Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
        return distancia;
    }

    @FXML
    public void Cancel(){
        btLinha.setSelected(false);
        suporte.clear();
        posicao.clear();
        canvas.getGraphicsContext2D().clearRect(0, 0,532,345);
        initialize();
    }

    @FXML
    public void Cadastrar(){
        try {
            ArrayList<Estrela> estrela = processResult();
            if (estrela.isEmpty() || estrela == null || linha.isEmpty()|| linha == null) {
                mensagem(Alert.AlertType.ERROR, "Failed to draw constellation!");
                posicao.clear();
                linha.clear();
                estrela.clear();
                btLinha.setSelected(false);
                canvas.getGraphicsContext2D().clearRect(0, 0,532,345);
            } else {
                mensagem(Alert.AlertType.CONFIRMATION, "Constellation added!");
                canvas.getGraphicsContext2D().clearRect(0, 0,532,345);
                btLinha.setSelected(false);
                for (Estrela star : estrela) {
                    JDBCEstrelaDAO.getInstance().create(star);
                    JDBCConstelacaoDAO.getInstance().Update2(star.getIdConstelacao());//seta lancado
                }
                System.out.println("--------------------------------------");
                for(Linha l: linha){
                    JDBCLinhaDAO.getInstance().create(l);
                }
                posicao.clear();
                initialize();
                cb.setItems(JDBCConstelacaoDAO.getInstance().List());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
