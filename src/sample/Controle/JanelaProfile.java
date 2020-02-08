package sample.Controle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import sample.Animacoes.JackInTheBox;
import sample.Modelo.JDBCUsuariosDAO;
import sample.Modelo.Usuario;

import java.io.IOException;

public class JanelaProfile extends ControleBase{

    @FXML
    private Label User;
    @FXML
    private AnchorPane Profile;
    @FXML
    private TextField tfNome, tfLogin, tfSenha2;
    @FXML
    private PasswordField tfSenha;
    @FXML
    private ToggleButton tg;
    @FXML
    private ColorPicker cp1, cp2;
    @FXML
    private Slider Audio;
    private Usuario u;
    private Color color1;
    private Color color2;

    public void initialize(){
        u = JDBCUsuariosDAO.getInstance().getLogado();
        User.setText("Live Long and Prosper, "+u.getNome()+"!");
        new JackInTheBox(User).setDelay(Duration.millis(300)).play();
        tfNome.setText(u.getNome());
        tfLogin.setText(u.getLogin());
        tfSenha.setText(u.getSenha());
        Audio.adjustValue(u.getAudio());
        color1 = Color.web(u.getCorEstrela());
        cp1.setValue(color1);
        color2 = Color.web(u.getCorLinha());
        cp2.setValue(color2);
    }

    @FXML
    public void Update(){
        u.setId(JDBCUsuariosDAO.getInstance().getLogado().getId());
        u.setNome(tfNome.getText());
        u.setLogin(tfLogin.getText());
        u.setSenha(tfSenha.getText());
        u.setAudio(Audio.getValue());
        u.setCorLinha("#"+Integer.toHexString(cp2.getValue().hashCode()));
        u.setCorEstrela("#"+Integer.toHexString(cp1.getValue().hashCode()));
        JDBCUsuariosDAO.getInstance().Update2(u);
        mensagem(Alert.AlertType.CONFIRMATION,"User updated!");
        Cancel();

        Voltar();
    }

    @FXML
    public void Cancel(){
        tfNome.setText(u.getNome());
        tfLogin.setText(u.getLogin());
        tfSenha.setText(u.getSenha());
        Audio.adjustValue(u.getAudio());
        color1 = Color.web(u.getCorEstrela());
        cp1.setValue(color1);
        color2 = Color.web(u.getCorLinha());
        cp2.setValue(color2);
    }

    @FXML
    private void showpass(){
        if(tg.isSelected()){
            tfSenha.setText("");
            tfSenha2.setText(u.getSenha());
        }else{
            tfSenha2.setText("");
            tfSenha.setText(u.getSenha());
        }
    }

    private void Voltar(){
        Cosmos().stop();
        try {
            Stage stage = (Stage)Profile.getScene().getWindow();
            Stage novo = new Stage();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../Visao/janelaUsuario.fxml"));
            Parent root = loader.load();
            novo.setScene(new Scene(root));
            novo.setTitle("CELESTIAL MAP");
            novo.setResizable(false);
            novo.show();
            stage.hide();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

