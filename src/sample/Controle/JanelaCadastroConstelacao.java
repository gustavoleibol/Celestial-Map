package sample.Controle;

import com.jfoenix.controls.JFXListView;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.Duration;
import sample.Animacoes.Flash;
import sample.Animacoes.JackInTheBox;
import sample.Modelo.Constelacao;
import sample.Modelo.JDBCConstelacaoDAO;

public class JanelaCadastroConstelacao extends ControleBase{
    @FXML
    private JFXListView<Constelacao> ltv;
    @FXML
    private TextArea text;
    @FXML
    private TextField description, img1, img2, img3, img4;
    @FXML
    private Label Register;
    private Constelacao c;

    public void initialize(){
        new Flash(Register).setDelay(Duration.millis(500)).play();
        c = null;
        ltv.setItems(JDBCConstelacaoDAO.getInstance().List());
    }

    public void Selecionar(){
        Constelacao aux = ltv.getSelectionModel().getSelectedItem();
        description.setText(aux.getDescricao());
        text.setText(aux.getTexto());
        img1.setText(aux.getImg1());
        img2.setText(aux.getImg2());
        img3.setText(aux.getImg3());
        img4.setText(aux.getImg4());
    }

    @FXML
    public void Register(){
        try{
            if(ltv.getSelectionModel().getSelectedItem() != null){
                c = new Constelacao();
                c.setId(ltv.getSelectionModel().getSelectedItem().getId());
                c.setDescricao(description.getText());
                c.setTexto(text.getText());
                c.setImg1(img1.getText());
                c.setImg2(img2.getText());
                c.setImg3(img3.getText());
                c.setImg4(img4.getText());
                JDBCConstelacaoDAO.getInstance().Update(c);
                mensagem(Alert.AlertType.CONFIRMATION,"Constellation Successfully Registered!");
                Cancel();
            }
            else{
                mensagem(Alert.AlertType.ERROR,"Something Wrong Happened.");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    public void Cancel(){
        description.setText("");
        text.setText("");
        img1.setText("");
        img2.setText("");
        img3.setText("");
        img4.setText("");
    }

}
