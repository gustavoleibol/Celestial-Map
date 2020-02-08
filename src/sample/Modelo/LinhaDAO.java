package sample.Modelo;

import javafx.collections.ObservableList;

import java.util.List;

public interface LinhaDAO {

    public void create(Linha l) throws Exception;
    public ObservableList<Linha> verifica(int a);
    public void clear(Linha l) throws  Exception;
}
