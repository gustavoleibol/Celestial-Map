package sample.Controle;

import javafx.animation.*;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.media.AudioClip;
import javafx.scene.transform.Shear;
import javafx.util.Duration;
import sample.Animacoes.AnimationFX;

import java.io.File;

public class ControleBase {

    private File img1;
    private File img2;
    private File img3;
    private File img4;

    AudioClip clip = null;

    protected void mensagem(Alert.AlertType tipo, String mensagem){
        Alert alerta = new Alert(tipo);
        alerta.setTitle("Alerta!!");
        alerta.setContentText(mensagem);
        alerta.showAndWait();
    }

    protected void makeFadeIn(Node painel){
        painel.setOpacity(0);
        FadeTransition fadeTransition = new FadeTransition();
        fadeTransition.setDuration(Duration.millis(1500));
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(1);
        fadeTransition.setNode(painel);
        fadeTransition.play();
    }

    protected AudioClip Cosmos() {
        try{
            String AUDIO_URL = getClass().getResource(
                    "/sample/Audios/1.mp3").toString();
            if(clip == null) {
                clip = new AudioClip(AUDIO_URL);// 1
            }else{
            }
            return clip;
        }catch (Exception E){
            E.printStackTrace();
        }
        return null;
    }

//    protected File Imagem1(int id){
//        if(id == 1){
//            img1 =
//        }
//    }

}
