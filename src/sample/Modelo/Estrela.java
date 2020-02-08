package sample.Modelo;

public class Estrela {

    private int id;
    private int idConstelacao;
    private double x;
    private double y;

    public Estrela(int id, int idConstelacao, double Y, double X){
        this.id = id;
        this.idConstelacao = idConstelacao;
        this.y = Y;
        this.x = X;
    }

    public Estrela(){

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

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }
}
