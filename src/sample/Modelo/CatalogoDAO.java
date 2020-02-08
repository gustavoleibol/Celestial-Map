package sample.Modelo;

import java.util.List;

public interface CatalogoDAO {
    public void Create(Catalogo c) throws Exception;
    public List<Catalogo> List();
    public Catalogo Search(int id) throws Exception;
    public Catalogo retornaUltimo() throws Exception;
}
