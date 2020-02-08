package sample.Modelo;

import javafx.beans.property.SimpleStringProperty;

public class Objeto {

    private int id;
    private int idConstelacao;
    private SimpleStringProperty objeto;
    private SimpleStringProperty designacao;
    private SimpleStringProperty significado;
    private SimpleStringProperty tipo;
    private SimpleStringProperty vmag;

    public Objeto(){
        objeto = new SimpleStringProperty();
        designacao = new SimpleStringProperty();
        significado = new SimpleStringProperty();
        tipo = new SimpleStringProperty();
        vmag = new SimpleStringProperty();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdConstelacao() {
        return idConstelacao;
    }

    public void setIdConstelacao(int idConstelacao) {
        this.idConstelacao = idConstelacao;
    }

    public String getObjeto() {
        return objeto.getValue();
    }

    public SimpleStringProperty getObjetoProperty() {
        return objeto;
    }

    public void setObjeto(String objeto) { this.objeto = new SimpleStringProperty(objeto); }

    public String getDesignacao() {
        return designacao.getValue();
    }

    public SimpleStringProperty getDesignacaoProperty() {
        return designacao;
    }

    public void setDesignacao(String designacao) { this.designacao = new SimpleStringProperty(designacao); }

    @Override
    public String toString() {
        return this.designacao.getValue();
    }

    public String getSignificado() {
        return significado.getValue();
    }

    public SimpleStringProperty getSignificadoProperty() {
        return significado;
    }

    public void setSignificado(String significado) { this.significado = new SimpleStringProperty(significado); }

    public String getTipo() {
        return tipo.getValue();
    }

    public SimpleStringProperty getTipoProperty() {
        return tipo;
    }

    public void setTipo(String tipo) { this.tipo = new SimpleStringProperty(tipo); }

    public String getVmag() {
        return vmag.getValue();
    }

    public SimpleStringProperty getVmagProperty() { return vmag; }

    public void setVmag(String vmag) { this.vmag = new SimpleStringProperty(vmag); }
}
