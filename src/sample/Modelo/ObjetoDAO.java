package sample.Modelo;

import java.util.List;

public interface ObjetoDAO {
    public void create(Objeto o) throws Exception;
    public List<Objeto> list(int id);
}

