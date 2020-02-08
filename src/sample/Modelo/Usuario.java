package sample.Modelo;

import java.io.Serializable;

public class Usuario implements Serializable{

    private int id;
    private String nome;
    private String login;
    private String senha;
    private double audio;
    private String corLinha;
    private String corEstrela;
    private int categoria;
    private Catalogo catalogo;

    public Usuario(String nome, String login, String senha, double audio, String corLinha, String corEstrela, int categoria, Catalogo catalogo) {
        this.nome = nome;
        this.login = login;
        this.senha = senha;
        this.audio = audio;
        this.corLinha = corLinha;
        this.corEstrela = corEstrela;
        this.categoria = categoria;
        this.catalogo = catalogo;
    }

    public Usuario(){
        this.nome = nome;
        this.login = login;
        this.senha = senha;
        this.audio = audio;
        this.corLinha = corLinha;
        this.corEstrela = corEstrela;
        this.categoria = categoria;
        this.catalogo = catalogo;
    }

    public int getCategoria() { return categoria; }

    public void setCategoria(int categoria) { this.categoria = categoria; }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public String getLogin() {
        return login;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) { this.senha = senha; }

    public double getAudio() { return audio; }

    public void setAudio(double audio) { this.audio = audio; }

    public String getCorLinha() { return corLinha; }

    public void setCorLinha(String corLinha) { this.corLinha = corLinha; }

    public String getCorEstrela() { return corEstrela; }

    public void setCorEstrela(String corEstrela) { this.corEstrela = corEstrela; }

    public Catalogo getCatalogo() {
        if(this.catalogo == null) {
            catalogo = JDBCCatalogoDAO.getInstance().Search(this.id);
        }
        return catalogo;
    }

    public void setCatalogo(Catalogo catalogo) {
        this.catalogo = catalogo;
    }

    public String toString(){
        return this.nome;
    }

}
