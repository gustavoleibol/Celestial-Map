package sample.Controle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import sample.Animacoes.JackInTheBox;
import sample.Animacoes.SlideInRight;
import sample.Modelo.JDBCUsuariosDAO;

import java.util.Random;

public class JanelaHubble extends ControleBase{

    @FXML
    private AnchorPane Hubble;
    @FXML
    private Label Fields;
    @FXML
    private Canvas canvas;

    public void initialize(){
        new JackInTheBox(Fields).setDelay(Duration.millis(200)).play();
        GraphicsContext gc = canvas.getGraphicsContext2D();
        drawShapes(gc);
    }

    @FXML
    public void Andromeda(){
        JDBCUsuariosDAO.getInstance().setHubble(1);
        Hubble.getChildren().clear();
        try{
            AnchorPane pane = FXMLLoader.load(getClass().getResource("../Visao/janelaWeb.fxml"));
            Hubble.getChildren().add(pane);
            new SlideInRight(Hubble).play();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void Westerlund2(){
        JDBCUsuariosDAO.getInstance().setHubble(2);
        Hubble.getChildren().clear();
        try{
            AnchorPane pane = FXMLLoader.load(getClass().getResource("../Visao/janelaWeb.fxml"));
            Hubble.getChildren().add(pane);
            new SlideInRight(Hubble).play();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void Crab(){
        JDBCUsuariosDAO.getInstance().setHubble(3);
        Hubble.getChildren().clear();
        try{
            AnchorPane pane = FXMLLoader.load(getClass().getResource("../Visao/janelaWeb.fxml"));
            Hubble.getChildren().add(pane);
            new SlideInRight(Hubble).play();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void Carina(){
        JDBCUsuariosDAO.getInstance().setHubble(4);
        Hubble.getChildren().clear();
        try{
            AnchorPane pane = FXMLLoader.load(getClass().getResource("../Visao/janelaWeb.fxml"));
            Hubble.getChildren().add(pane);
            new SlideInRight(Hubble).play();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void Deep(){
        JDBCUsuariosDAO.getInstance().setHubble(5);
        Hubble.getChildren().clear();
        try{
            AnchorPane pane = FXMLLoader.load(getClass().getResource("../Visao/janelaWeb.fxml"));
            Hubble.getChildren().add(pane);
            new SlideInRight(Hubble).play();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void Pillars(){
        JDBCUsuariosDAO.getInstance().setHubble(6);
        Hubble.getChildren().clear();
        try{
            AnchorPane pane = FXMLLoader.load(getClass().getResource("../Visao/janelaWeb.fxml"));
            Hubble.getChildren().add(pane);
            new SlideInRight(Hubble).play();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void Sombrero(){
        JDBCUsuariosDAO.getInstance().setHubble(7);
        Hubble.getChildren().clear();
        try{
            AnchorPane pane = FXMLLoader.load(getClass().getResource("../Visao/janelaWeb.fxml"));
            Hubble.getChildren().add(pane);
            new SlideInRight(Hubble).play();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void Helix(){
        JDBCUsuariosDAO.getInstance().setHubble(8);
        Hubble.getChildren().clear();
        try{
            AnchorPane pane = FXMLLoader.load(getClass().getResource("../Visao/janelaWeb.fxml"));
            Hubble.getChildren().add(pane);
            new SlideInRight(Hubble).play();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void CassiopeiaA(){
        JDBCUsuariosDAO.getInstance().setHubble(9);
        Hubble.getChildren().clear();
        try{
            AnchorPane pane = FXMLLoader.load(getClass().getResource("../Visao/janelaWeb.fxml"));
            Hubble.getChildren().add(pane);
            new SlideInRight(Hubble).play();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void Supernova(){
        JDBCUsuariosDAO.getInstance().setHubble(10);
        Hubble.getChildren().clear();
        try{
            AnchorPane pane = FXMLLoader.load(getClass().getResource("../Visao/janelaWeb.fxml"));
            Hubble.getChildren().add(pane);
            new SlideInRight(Hubble).play();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void drawShapes(GraphicsContext gc) {//desenhando potinhos fakes
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());
        Random rnd = new Random(System.currentTimeMillis());
        gc.setFill(Color.GRAY);
        for (int i = 0; i < 500; i++) {
            double x = rnd.nextDouble() * 705;
            double y = rnd.nextDouble() * 3561;
            double t = rnd.nextDouble() * 5;
            gc.fillOval(x, y, t, t);
        }
    }

}
