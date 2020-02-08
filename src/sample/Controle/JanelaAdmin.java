package sample.Controle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import sample.Animacoes.Jello;
import sample.Animacoes.SlideInRight;
import sample.Modelo.JDBCUsuariosDAO;

import java.io.IOException;

public class JanelaAdmin extends ControleBase{

    @FXML
    private AnchorPane Admin, Navegacao;
    @FXML
    private Button Register, Draw, Cosmic, Manage, Logout;

    public void initialize(){
        Navegacao.getChildren().clear();
        try{
            AnchorPane pane = FXMLLoader.load(getClass().getResource("../Visao/janelaCadastroConstelacao.fxml"));
            Navegacao.getChildren().add(pane);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void Register(){
        new Jello(Register).setDelay(Duration.millis(600)).play();
        Navegacao.getChildren().clear();
        try{
            AnchorPane pane = FXMLLoader.load(getClass().getResource("../Visao/janelaCadastroConstelacao.fxml"));
            Navegacao.getChildren().add(pane);
            new SlideInRight(Navegacao).play();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void Draw(){
        new Jello(Draw).setDelay(Duration.millis(600)).play();
        Navegacao.getChildren().clear();
        try{
            AnchorPane pane = FXMLLoader.load(getClass().getResource("../Visao/janelaDesenharConstelacao.fxml"));
            Navegacao.getChildren().add(pane);
            new SlideInRight(Navegacao).play();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void Register2(){
        new Jello(Cosmic).setDelay(Duration.millis(600)).play();
        Navegacao.getChildren().clear();
        try{
            AnchorPane pane = FXMLLoader.load(getClass().getResource("../Visao/janelaObjeto.fxml"));
            Navegacao.getChildren().add(pane);
            new SlideInRight(Navegacao).play();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void Manager(){
        new Jello(Manage).setDelay(Duration.millis(600)).play();
        Navegacao.getChildren().clear();
        try{
            AnchorPane pane = FXMLLoader.load(getClass().getResource("../Visao/janelaManager.fxml"));
            Navegacao.getChildren().add(pane);
            new SlideInRight(Navegacao).play();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void Backup(){
        Navegacao.getChildren().clear();
        try{
            AnchorPane pane = FXMLLoader.load(getClass().getResource("../Visao/janelaBackup.fxml"));
            Navegacao.getChildren().add(pane);
            new SlideInRight(Navegacao).play();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void Logout(){
        new Jello(Logout).setDelay(Duration.millis(600)).play();
        JDBCUsuariosDAO.getInstance().logout();
        try {
            Stage stage = (Stage)Admin.getScene().getWindow();
            Stage novo = new Stage();
            novo.initStyle(StageStyle.UNDECORATED);
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../Visao/janelaPrincipal.fxml"));
            Parent root = loader.load();
            novo.setScene(new Scene(root));
            novo.setResizable(false);
            novo.show();
            stage.hide();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
