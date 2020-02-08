package sample.Modelo;

import java.util.List;

public interface CuriosidadeDAO {
    public List<Curiosidade> list();
    public void clear(Curiosidade c) throws  Exception;
}
