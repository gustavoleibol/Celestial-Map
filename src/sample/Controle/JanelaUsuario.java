package sample.Controle;

import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import org.omg.CORBA.PRIVATE_MEMBER;
import sample.Animacoes.*;
import sample.Modelo.*;


import java.io.File;
import java.io.IOException;
import java.util.Random;

public class JanelaUsuario extends ControleBase {

    @FXML
    private AnchorPane Usuario, Navegacao;
    @FXML
    private Label usuario, titulo, descricao;
    @FXML
    private Button Search, Catalog, Hubble, Profile, Logout;
    @FXML
    private Canvas Canvas;
    @FXML
    private ImageView img1, img2, img3, img4, imagem, terra;
    private Image img;
    private Curiosidade curiosidade;


    public void initialize(){
        new Jello(terra).setDelay(Duration.millis(600)).play();
        Curiosidades();
        clip = Cosmos();
        clip.setVolume(JDBCUsuariosDAO.getInstance().getLogado().getAudio()/100);
        clip.play();
        makeFadeIn(Usuario);
        usuario.setText("Welcome " + JDBCUsuariosDAO.getInstance().getLogado().getNome() + "! Did You Know?");
        GraphicsContext gc = Canvas.getGraphicsContext2D();
        drawShapes(gc);
    }

    @FXML
    public void Maps(){
        new Jello(img1).setDelay(Duration.millis(200)).play();
        new Jello(Search).setDelay(Duration.millis(200)).play();
        Navegacao.getChildren().clear();
        try{
            AnchorPane pane = FXMLLoader.load(getClass().getResource("../Visao/janelaMaps.fxml"));
            Navegacao.getChildren().add(pane);
            new SlideInRight(Navegacao).play();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void Catalogo(){
        new Jello(img2).setDelay(Duration.millis(200)).play();
        new Jello(Catalog).setDelay(Duration.millis(200)).play();
        Navegacao.getChildren().clear();
        try{
            AnchorPane pane = FXMLLoader.load(getClass().getResource("../Visao/janelaCatalogo.fxml"));
            Navegacao.getChildren().add(pane);
            new SlideInRight(Navegacao).play();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void Hubble(){
        new Jello(img3).setDelay(Duration.millis(200)).play();
        new Jello(Hubble).setDelay(Duration.millis(200)).play();
        Navegacao.getChildren().clear();
        try{
            AnchorPane pane = FXMLLoader.load(getClass().getResource("../Visao/janelaHubble.fxml"));
            Navegacao.getChildren().add(pane);
            new SlideInRight(Navegacao).play();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    public void Profile(){
        new Jello(img4).setDelay(Duration.millis(200)).play();
        new Jello(Profile).setDelay(Duration.millis(200)).play();
        Navegacao.getChildren().clear();
        try{
            AnchorPane pane = FXMLLoader.load(getClass().getResource("../Visao/janelaProfile.fxml"));
            Navegacao.getChildren().add(pane);
            new SlideInRight(Navegacao).play();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void Curiosidades(){
        try {
            curiosidade = JDBCCuriosidadeDAO.getInstance().list2();
            int idc = curiosidade.getId();
            String tituloc = curiosidade.getTitulo();
            String descricaoc = curiosidade.getDescricao();
            titulo.setText(tituloc);
            descricao.setText(descricaoc);
            File f = new File("src/sample/Curiosidades/"+idc+".jpg");
            img= new Image(f.toURI().toString());
            imagem.setImage(img);
            new ZoomIn(titulo).play();
            new ZoomInDown(descricao).play();
            new ZoomIn(imagem).play();
        }catch (Exception e){
            e.printStackTrace();
        }
        imagem.setVisible(true);
    }

    private void drawShapes(GraphicsContext gc) {//desenhando potinhos fakes
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());
        Random rnd = new Random(System.currentTimeMillis());
        gc.setFill(Color.GRAY);
        for (int i = 0; i < 150; i++) {
            double x = rnd.nextDouble() * 730;
            double y = rnd.nextDouble() * 600;
            double t = rnd.nextDouble() * 5;
            gc.fillOval(x, y, t, t);
        }
    }

    @FXML
    public void Next(){
        Curiosidades();
    }

    @FXML
    public void Home(){
        clip.stop();
        Usuario.getChildren().clear();
        try{
            AnchorPane pane = FXMLLoader.load(getClass().getResource("../Visao/janelaUsuario.fxml"));
            Usuario.getChildren().add(pane);
            new SlideInRight(Usuario).play();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void Logout(){
        new Jello(Logout).play();
        Cosmos().stop();
        JDBCUsuariosDAO.getInstance().logout();
        try{
            Stage stage = (Stage)Usuario.getScene().getWindow();
            Stage novo = new Stage();
            novo.initStyle(StageStyle.UNDECORATED);
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../Visao/janelaPrincipal.fxml"));
            Parent root = loader.load();
            novo.setScene(new Scene(root));
            novo.setResizable(false);
            novo.show();
            stage.hide();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
