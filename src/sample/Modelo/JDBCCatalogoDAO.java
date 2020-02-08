package sample.Modelo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class JDBCCatalogoDAO implements CatalogoDAO {

    private static JDBCCatalogoDAO instance;
    private ObservableList<Catalogo> lista;

    private JDBCCatalogoDAO(){
        lista = FXCollections.observableArrayList();
    }

    public static JDBCCatalogoDAO getInstance(){
        if(instance==null){
            instance = new JDBCCatalogoDAO();
        }
        return instance;
    }

    @Override
    public void Create(Catalogo catalogo) throws Exception {
        Connection c = FabricaConexao.getConnection();

        String sql = "insert into" +
                " Catalogo()" +
                "values()";

        System.out.println("cadastro no banco table: Catalogo");
        PreparedStatement pStm = c.prepareStatement(sql);
        pStm.execute();
        pStm.close();

        c.close();
    }

    @Override
    public ObservableList<Catalogo> List() {
        lista.clear();
        try {
            Connection c = FabricaConexao.getConnection();
            Statement stm = c.createStatement();
            ResultSet rs = stm.executeQuery("select * from Catalogo");
            while (rs.next()){
                Catalogo catalogo = montaCatalogo(rs);
                lista.add(catalogo);
            }
            rs.close();
            stm.close();
            c.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return lista;
    }

    @Override
    public Catalogo Search(int id) {
        Catalogo catalogo = null;
        try {
            Connection c = FabricaConexao.getConnection();
            Statement stm = c.createStatement();
            ResultSet rs = stm.executeQuery("select * from Catalogo where id = "+id);
            while (rs.next()){
                catalogo = montaCatalogo(rs);
                return catalogo;
            }
            rs.close();
            stm.close();
            c.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return catalogo;
    }

    @Override
    public Catalogo retornaUltimo(){
        Catalogo catalogo = null;
        try {
            Connection c = FabricaConexao.getConnection();
            Statement stm = c.createStatement();
            ResultSet rs = stm.executeQuery("select * from Catalogo order by id desc limit 1");
            while (rs.next()){
                catalogo = montaCatalogo(rs);
                return catalogo;
            }
            rs.close();
            stm.close();
            c.close();
            return catalogo;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return catalogo;
    }

    public Catalogo montaCatalogo(ResultSet rs) throws SQLException{
        int id = rs.getInt("id");
        Catalogo catalogo = new Catalogo();
        catalogo.setId(id);
        return catalogo;
    }

}