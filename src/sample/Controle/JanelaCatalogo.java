package sample.Controle;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import sample.Animacoes.JackInTheBox;
import sample.Animacoes.SlideInRight;
import sample.Modelo.*;

import java.io.File;
import java.io.IOException;
import java.util.Random;

public class JanelaCatalogo {

    @FXML
    private ImageView img1, img2, img3, img4;
    @FXML
    private Label const1, const2, const3, const4, Constellation,  User;
    @FXML
    private AnchorPane Catalogo;
    @FXML
    private Canvas canvas;
    private ObservableList<Constelacao> aux;
    private int contador = 0, cont =0;

    public void initialize() {
        contador=0;
        cont=0;
        new JackInTheBox(Constellation).setDelay(Duration.millis(200)).play();
        User.setText(JDBCUsuariosDAO.getInstance().getLogado().getNome());
        GraphicsContext gc = canvas.getGraphicsContext2D();
        drawShapes(gc);
        initial();
    }

    private void initial(){
        //aux com constelacoes catalogadas
        aux = JDBCUsuariosDAO.getInstance().getLogado().getCatalogo().getConstelacao();


        //contando as constelaçoes
        for(int i = cont ;i<aux.size();i++){
            contador++;
        }
        System.out.println("contador que ele catalogou "+contador);
        if(contador > 0) {
            for (int i = 0; i < contador; i++) {
                File f = new File("src/sample/Id/"+aux.get(i+cont).getId()+".jpg");
                Image img = new Image(f.toURI().toString());
                if(i == 0) {
                    img1.setImage(img);
                    img1.setVisible(true);
                    Constelacao constelacao = JDBCConstelacaoDAO.getInstance().Search(aux.get(i+cont).getId());
                    const1.setText(constelacao.getNome());
                }

                if(i == 1) {
                    img2.setImage(img);
                    img2.setVisible(true);
                    Constelacao constelacao = JDBCConstelacaoDAO.getInstance().Search(aux.get(i+cont).getId());
                    const2.setText(constelacao.getNome());
                }
                if (i == 2) {
                    img3.setImage(img);
                    img3.setVisible(true);
                    Constelacao constelacao = JDBCConstelacaoDAO.getInstance().Search(aux.get(i+cont).getId());
                    const3.setText(constelacao.getNome());
                }
                if(i == 3) {
                    img4.setImage(img);
                    img4.setVisible(true);
                    Constelacao constelacao = JDBCConstelacaoDAO.getInstance().Search(aux.get(i+cont).getId());
                    const4.setText(constelacao.getNome());
                }
            }
        }else{
            File f = new File("src/sample/Imagens/Interrogacao.png");
            Image img = new Image(f.toURI().toString());
            img1.setImage(img);
            const1.setText("?");
            img2.setImage(img);
            const2.setText("?");
            img3.setImage(img);
            const3.setText("?");
            img4.setImage(img);
            const4.setText("?");
        }
    }

    @FXML
    private void Button1(){
        JDBCCatalogo_has_ConstelacaoDAO.getInstance().setBotao(aux.get(cont+0));
        Catalogo.getChildren().clear();
        try{
            ScrollPane pane = FXMLLoader.load(getClass().getResource("../Visao/janelaCatalogoInfo.fxml"));
            Catalogo.getChildren().add(pane);
            new SlideInRight(Catalogo).play();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void Button2(){
        JDBCCatalogo_has_ConstelacaoDAO.getInstance().setBotao(aux.get(cont+1));
        Catalogo.getChildren().clear();
        try{
            ScrollPane pane = FXMLLoader.load(getClass().getResource("../Visao/janelaCatalogoInfo.fxml"));
            Catalogo.getChildren().add(pane);
            new SlideInRight(Catalogo).play();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void Button3(){
        JDBCCatalogo_has_ConstelacaoDAO.getInstance().setBotao(aux.get(cont+2));
        Catalogo.getChildren().clear();
        try{
            ScrollPane pane = FXMLLoader.load(getClass().getResource("../Visao/janelaCatalogoInfo.fxml"));
            Catalogo.getChildren().add(pane);
            new SlideInRight(Catalogo).play();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void Button4(){
        JDBCCatalogo_has_ConstelacaoDAO.getInstance().setBotao(aux.get(cont+3));
        Catalogo.getChildren().clear();
        try{
            ScrollPane pane = FXMLLoader.load(getClass().getResource("../Visao/janelaCatalogoInfo.fxml"));
            Catalogo.getChildren().add(pane);
            new SlideInRight(Catalogo).play();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void Next(){
        if(cont < 88) {
            cont = cont + 4;
            contador = 0;
            File f = new File("src/sample/Imagens/Interrogacao.png");
            Image img = new Image(f.toURI().toString());
            img1.setImage(img);
            const1.setText("?");
            img2.setImage(img);
            const2.setText("?");
            img3.setImage(img);
            const3.setText("?");
            img4.setImage(img);
            const4.setText("?");
            initial();
        }
    }

    private void drawShapes(GraphicsContext gc) {//desenhando potinhos fakes
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());
        Random rnd = new Random(System.currentTimeMillis());
        gc.setFill(Color.GRAY);
        for (int i = 0; i < 200; i++) {
            double x = rnd.nextDouble() * 705;
            double y = rnd.nextDouble() * 536;
            double t = rnd.nextDouble() * 5;
            gc.fillOval(x, y, t, t);
        }
    }

    @FXML
    public void Back(){
        if(cont > 0) {
            cont = cont - 4;
            contador = 0;
            File f = new File("src/sample/Imagens/Interrogacao.png");
            Image img = new Image(f.toURI().toString());
            img1.setImage(img);
            const1.setText("?");
            img2.setImage(img);
            const2.setText("?");
            img3.setImage(img);
            const3.setText("?");
            img4.setImage(img);
            const4.setText("?");
            initial();
        }
    }
}