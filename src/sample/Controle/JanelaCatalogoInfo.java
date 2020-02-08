package sample.Controle;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import sample.Animacoes.JackInTheBox;
import sample.Animacoes.ZoomIn;
import sample.Animacoes.ZoomInDown;
import sample.Modelo.*;

import java.io.File;
import java.util.Random;

public class JanelaCatalogoInfo extends ControleBase {

    @FXML
    private Canvas canvas;
    @FXML
    private Label descricao,desc1,desc2,desc3,desc4,constelacao,texto;
    @FXML
    private ImageView imagem1, imagem2, imagem3, imagem4;
    @FXML
    private TableView<Objeto> tv;
    @FXML
    private TableColumn<Objeto,String> obj, nome, significado, tipo, vmag;
    private ObservableList<Objeto> aux;
    private Image img1, img2, img3, img4;

    public void initialize(){
        GraphicsContext gc = canvas.getGraphicsContext2D();
        draw(gc);
        //PEGANDO CONSTELAÇÃO DO BOTÃO CLICADO
        Constelacao botao = JDBCConstelacaoDAO.getInstance().Search(JDBCCatalogo_has_ConstelacaoDAO.getInstance().getBotao().getId());

        //SETANDO INFORMAÇÕES DA CONSTELAÇÃO
        constelacao.setText(botao.getNome());
        new JackInTheBox(constelacao).setDelay(Duration.millis(200)).play();
        descricao.setText(botao.getDescricao());
        new ZoomIn(descricao).setDelay(Duration.millis(300)).play();
        texto.setText(botao.getTexto());
        new ZoomInDown(texto).setDelay(Duration.millis(100)).play();
        desc1.setText(botao.getImg1());
        desc2.setText(botao.getImg2());
        desc3.setText(botao.getImg3());
        desc4.setText(botao.getImg4());

        //IMAGENS DA CONSTELAÇÃO
        String minuscula = botao.getNome().toLowerCase();
        System.out.println(minuscula);
        File f1 = new File("src/sample/Constelacoes/"+minuscula+1+".jpg");
        File f2 = new File("src/sample/Constelacoes/"+minuscula+2+".png");
        File f3 = new File("src/sample/Constelacoes/"+minuscula+3+".jpg");
        File f4 = new File("src/sample/Constelacoes/"+minuscula+4+".jpg");
        img1 = new Image(f1.toURI().toString());
        img2 = new Image(f2.toURI().toString());
        img3 = new Image(f3.toURI().toString());
        img4 = new Image(f4.toURI().toString());
        imagem1.setImage(img1);
        imagem2.setImage(img2);
        imagem3.setImage(img3);
        imagem4.setImage(img4);


        //TABLE VIEW DE OBJETOS CÓSMICOS
        obj.setCellValueFactory(new PropertyValueFactory<>("objeto"));
        nome.setCellValueFactory(new PropertyValueFactory<>("designacao"));
        significado.setCellValueFactory(new PropertyValueFactory<>("significado"));
        tipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));
        vmag.setCellValueFactory(new PropertyValueFactory<>("vmag"));

        tv.setItems(JDBCObjetoDAO.getInstance().list(botao.getId()));
    }

    private void draw(GraphicsContext gc) {
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());

        Random rnd = new Random(System.currentTimeMillis());
        gc.setFill(Color.WHITE);
        for (int i = 0; i < 130; i++) {
            double x = rnd.nextDouble() * 705;
            double y = rnd.nextDouble() * 1683;
            double t = rnd.nextDouble() * 5;
            gc.fillOval(x, y, t, t);
        }
    }
}
