package sample.Controle;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.util.Duration;
import sample.Animacoes.Flash;
import sample.Modelo.Constelacao;
import sample.Modelo.JDBCConstelacaoDAO;
import sample.Modelo.JDBCObjetoDAO;
import sample.Modelo.Objeto;

public class JanelaObjeto extends ControleBase{

    @FXML
    private ListView<Constelacao> ltv;
    @FXML
    private ListView<Objeto> ltv2;
    @FXML
    private Label constellation, Register;
    @FXML
    private TextField object, designation, meaning, type, vmag;
    private Constelacao c;
    private Objeto obj;

    public void initialize(){
        new Flash(Register).setDelay(Duration.millis(500)).play();
        c = null;
        obj = null;
        ltv.setItems(JDBCConstelacaoDAO.getInstance().List());
    }

    @FXML
    public void Selecionar(){
        object.setText("");
        designation.setText("");
        type.setText("");
        vmag.setText("");
        meaning.setText("N/A");
        c = ltv.getSelectionModel().getSelectedItem();
        constellation.setText(c.getNome());
        ltv2.setItems(JDBCObjetoDAO.getInstance().list(c.getId()));
    }

    @FXML
    public void Selecionar2(){
        obj = ltv2.getSelectionModel().getSelectedItem();
        object.setText(obj.getObjeto());
        designation.setText(obj.getDesignacao());
        meaning.setText(obj.getSignificado());
        type.setText(obj.getTipo());
        vmag.setText(obj.getVmag());
    }

    @FXML
    public void Register(){
        try {
            if (c != null) {
                obj = new Objeto();
                obj.setIdConstelacao(c.getId());
                obj.setObjeto(object.getText());
                obj.setDesignacao(designation.getText());
                obj.setSignificado(meaning.getText());
                obj.setTipo(type.getText());
                obj.setVmag(vmag.getText());
                JDBCObjetoDAO.getInstance().create(obj);
                mensagem(Alert.AlertType.CONFIRMATION,"Object Successfully Registered!");
                ltv2.setItems(JDBCObjetoDAO.getInstance().list(c.getId()));
                Cancel();
            }
            else {
                mensagem(Alert.AlertType.ERROR,"Something Wrong Happened.");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    public void Cancel(){
        object.setText("");
        designation.setText("");
        meaning.setText("N/A");
        type.setText("");
        vmag.setText("");
    }
}
