package sample.Modelo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Catalogo {

    private int id;
    private ObservableList<Constelacao> constelacao;


    public Catalogo(){
        constelacao = FXCollections.observableArrayList();
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ObservableList<Constelacao> getConstelacao() {
        constelacao = JDBCConstelacaoDAO.getInstance().buscaPorCatalogo(id);
        return constelacao;
    }

    /*public void adicionarConstelacao(Constelacao c){
        constelacao.add(c);
    }

    public void clearConstelacao(){
        constelacao.clear();
    }*/

}
