package sample.Controle;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.paint.Color;
import sample.Modelo.ControleBackup;

import java.util.Random;

public class JanelaBackup extends ControleBase{

    @FXML
    private Canvas canvas;
    @FXML
    private ComboBox<String> cb;
    public void initialize(){
        cb.setItems(ControleBackup.getInstance().retornaTabelas());
        GraphicsContext gc = canvas.getGraphicsContext2D();
        drawShapes(gc);
    }
    @FXML
    private void Salvar(){
        try {
            if (cb.getSelectionModel().getSelectedItem() == "Usuarios") {
                ControleBackup.getInstance().salvarTXT("Usuarios");
            }
            mensagem(Alert.AlertType.CONFIRMATION, "Save File!");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    private void Ler(){
        try {
            if (cb.getSelectionModel().getSelectedItem() == "Usuarios") {
                ControleBackup.getInstance().lerTXT("Usuarios");
            }
            mensagem(Alert.AlertType.CONFIRMATION, "Read File!");
        }catch (Exception e){
            e.printStackTrace();
        }
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

}
