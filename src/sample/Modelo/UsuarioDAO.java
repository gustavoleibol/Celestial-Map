package sample.Modelo;

import javafx.collections.ObservableList;

import java.util.List;

public interface UsuarioDAO {
    public void Create(Usuario j) throws Exception;
    public List<Usuario> List();
    public ObservableList<Usuario> Search(String busca) throws Exception;
    public void Clear(Usuario j) throws  Exception;
    public void Update(Usuario u) throws Exception;
    public void Update2(Usuario u) throws Exception;
}
