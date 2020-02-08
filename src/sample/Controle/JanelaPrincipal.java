package sample.Controle;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import sample.Animacoes.ZoomInDown;
import sample.Modelo.Catalogo;
import sample.Modelo.JDBCCatalogoDAO;
import sample.Modelo.JDBCUsuariosDAO;
import sample.Modelo.Usuario;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class JanelaPrincipal extends ControleBase {

    @FXML
    private JFXTextField tfLogin, cLogin, cNome;
    @FXML
    private JFXPasswordField tfSenha, cSenha;
    @FXML
    private AnchorPane Principal, Login, Cadastro;
    @FXML
    private MediaView mv;
    @FXML
    private Label txt1,txt2,txt3;
    private MediaPlayer mediaPlay;

    public void initialize(){
        try{
            new ZoomInDown(txt1).play();
            new ZoomInDown(txt2).play();
            new ZoomInDown(txt3).play();
            Cadastro.setVisible(false);
            File f = new File("binary.mp4");
            Media media = new Media(f.toURI().toString());
            mediaPlay = new MediaPlayer(media);
            mv.setMediaPlayer(mediaPlay);
            mediaPlay.play();
            mediaPlay.setCycleCount(mediaPlay.INDEFINITE);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    private void Logar() {
        String login = tfLogin.getText();
        String senha = tfSenha.getText();
        Usuario u = JDBCUsuariosDAO.getInstance().logar(login, senha);
        if(u == null||login.isEmpty()||senha.isEmpty()||login.isEmpty() && senha.isEmpty()){
            mensagem(Alert.AlertType.ERROR, "User does not exist!");
        }
        else if(u.getCategoria() == 0){//admin
            JDBCUsuariosDAO.getInstance().setLogado(u);
            mediaPlay.stop();
            JanelaAdmin();
        }
        else if(u.getCategoria() == 1){//user
            JDBCUsuariosDAO.getInstance().setLogado(u);
            mediaPlay.stop();
            JanelaUsuario();
        }
    }

    public void JanelaUsuario(){
        try{
            Stage stage = (Stage)Principal.getScene().getWindow();
            Stage novo = new Stage();
            novo.initStyle(StageStyle.DECORATED);
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../Visao/janelaUsuario.fxml"));
            Parent root = loader.load();
            novo.setScene(new Scene(root));
            novo.setTitle("CELESTIAL MAP");
            novo.setResizable(false);
            novo.show();
            stage.hide();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void JanelaAdmin(){
        try{
            Stage stage = (Stage)Principal.getScene().getWindow();
            Stage novo = new Stage();
            novo.initStyle(StageStyle.DECORATED);
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../Visao/janelaAdmin.fxml"));
            Parent root = loader.load();
            novo.setScene(new Scene(root));
            novo.setTitle("A D M I N");
            novo.setResizable(false);
            novo.show();
            stage.hide();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    public void Cadastrar(){
        new ZoomInDown(Cadastro).play();
        Cadastro.setVisible(true);
    }

    @FXML
    public void btCadastrar(){
        Usuario u = processResult();
            if(u != null){
                try {
                    Catalogo c = new Catalogo();
                    JDBCCatalogoDAO.getInstance().Create(c);
                    u.setCatalogo(JDBCCatalogoDAO.getInstance().retornaUltimo());
                    JDBCUsuariosDAO.getInstance().Create(u);
                    mensagem(Alert.AlertType.CONFIRMATION, "User registered successfully!");
                    new ZoomInDown(Login).play();
                } catch (Exception e) {
                    mensagem(Alert.AlertType.ERROR, "Invalid registration!");
                    e.printStackTrace();
                }
            }
            cLogin.setText("");
            cNome.setText("");
            cSenha.setText("");
            Cadastro.setVisible(false);
    }

    public Usuario processResult(){
        String nome = cNome.getText();
        String login = cLogin.getText();
        String senha = cSenha.getText();
        double audio = 70;
        String corEstrela = "#42b0f4";
        String corLinha = "#00ddff";
        int categoria = 1;
        Catalogo catalogo = null;
        if(nome.isEmpty() && login.isEmpty() && senha.isEmpty()|| nome.isEmpty()|| login.isEmpty()||senha.isEmpty()){
            mensagem(Alert.AlertType.ERROR,"Invalid registration!");
            cLogin.setText("");
            cNome.setText("");
            cSenha.setText("");
        }
        else{
            Usuario u = new Usuario(nome, login, senha, audio, corLinha, corEstrela, categoria, catalogo);
            return u;
        }
        return null;
    }

    @FXML
    public void Sair(){ Platform.exit(); }

    @FXML
    public void Cancel(){
        Cadastro.setVisible(false); }
}
