package sample.Controle;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXToggleButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import sample.Animacoes.Flash;
import sample.Modelo.JDBCUsuariosDAO;
import sample.Modelo.Usuario;

import java.io.IOException;

public class JanelaManager extends ControleBase {

    @FXML
    private TextField nome, login, senha2, busca;
    @FXML
    private JFXPasswordField senha;
    @FXML
    private JFXToggleButton tg;
    @FXML
    private ListView<Usuario> ltv;
    @FXML
    private Label Manage;
    private Usuario u=null;

    public void initialize(){
        try {
            new Flash(Manage).setDelay(Duration.millis(500)).play();
            u = null;
            ltv.setItems(JDBCUsuariosDAO.getInstance().List());
            nome.setText("");
            login.setText("");
            senha.setText("");
            senha2.setText("");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    private void buscar(){
        String aux = busca.getText();
        System.out.println(aux);
        ltv.getItems().clear();
        nome.setText("");
        login.setText("");
        senha.setText("");
        senha2.setText("");
        u = null;
        ltv.setItems(JDBCUsuariosDAO.getInstance().Search(aux));
    }
    @FXML
    private void selecionar(){
        senha2.setText("");
        u = ltv.getSelectionModel().getSelectedItem();
        nome.setText(u.getNome());
        login.setText(u.getLogin());
        if(tg.isSelected()){
            senha.setText("");
            senha2.setText(u.getSenha());
        }else{
            senha2.setText("");
            senha.setText(u.getSenha());
        }
    }

    @FXML
    private void deletar(){
        JDBCUsuariosDAO.getInstance().Clear(u);
        mensagem(Alert.AlertType.CONFIRMATION,"Usuário Deletado!");
        initialize();
    }

    @FXML
    private void alterar(){
        u.setLogin(login.getText());
        u.setNome(nome.getText());
        u.setSenha(senha.getText());
        JDBCUsuariosDAO.getInstance().Update(u);
        mensagem(Alert.AlertType.CONFIRMATION,"Usuário Alterado!");
        initialize();
    }

    @FXML
    private void showpass(){
        if(tg.isSelected()){
            senha.setText("");
            senha2.setText(u.getSenha());
        }else{
            senha2.setText("");
            senha.setText(u.getSenha());
        }
    }
}
