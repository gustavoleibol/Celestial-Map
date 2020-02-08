package sample.Modelo;

import javafx.collections.ObservableList;

public class Catalogo_has_Constelacao {

    private int id;
    private Catalogo catalogo;
    private Constelacao constelacao;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public Catalogo getCatalogo() {
        return catalogo;
    }

    public void setCatalogo(Catalogo catalogo) {
        this.catalogo = catalogo;
    }

    public Constelacao getConstelacao() {
        return constelacao;
    }

    public void setConstelacao(Constelacao constelacao) {
        this.constelacao = constelacao;
    }
}
