package sample.Modelo;

import java.util.List;

public interface EstrelaDAO {

    public void create(Estrela e) throws Exception;
    public int list();
    public void clear(Estrela e) throws  Exception;
}
