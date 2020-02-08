package sample.Modelo;

import java.sql.ResultSet;
import java.util.List;

public interface ConstelacaoDAO {
    public void Create(Constelacao c) throws Exception;
    public void Update(Constelacao c) throws Exception;
    public void Update2(int id) throws Exception;
    public Constelacao Search(int id) throws Exception;
    public List<Constelacao> List();
    public List<Constelacao> List2();
    public List<Integer> retornaFeitas();
    public List<Constelacao> buscaPorCatalogo(int idCatalogo);
}
