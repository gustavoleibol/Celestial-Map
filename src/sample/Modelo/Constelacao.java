package sample.Modelo;

public class Constelacao {

    private int id;
    private String nome;
    private int lancado;
    private int qtdLinhas;
    private String descricao;
    private String texto;
    private String img1;
    private String img2;
    private String img3;

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public String getImg1() {
        return img1;
    }

    public void setImg1(String img1) {
        this.img1 = img1;
    }

    public String getImg2() {
        return img2;
    }

    public void setImg2(String img2) {
        this.img2 = img2;
    }

    public String getImg3() {
        return img3;
    }

    public void setImg3(String img3) {
        this.img3 = img3;
    }

    public String getImg4() {
        return img4;
    }

    public void setImg4(String img4) {
        this.img4 = img4;
    }

    private String img4;

    public int getLancado() { return lancado; }

    public void setLancado(int lancado) { this.lancado = lancado; }

    public String getDescricao() { return descricao; }

    public void setDescricao(String descricao) { this.descricao = descricao; }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return this.nome;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getQtdLinhas() {
        return qtdLinhas;
    }

    public void setQtdLinhas(int qtdEstrelas) {
        this.qtdLinhas = qtdEstrelas;
    }
}
