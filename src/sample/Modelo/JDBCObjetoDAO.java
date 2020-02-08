package sample.Modelo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class JDBCObjetoDAO implements ObjetoDAO {

    private static JDBCObjetoDAO instance;
    private ObservableList<Objeto> lista;

    private JDBCObjetoDAO(){
        lista = FXCollections.observableArrayList();
    }

    public static JDBCObjetoDAO getInstance(){
        if(instance==null){
            instance = new JDBCObjetoDAO();
        }
        return instance;
    }

    @Override
    public void create(Objeto o) throws Exception {
        Connection c = FabricaConexao.getConnection();

        String sql = "insert into" +
                " Objeto(idConstelacao,objeto,designacao,significado,tipo,vmag)" +
                "values(?,?,?,?,?,?)";

        System.out.println("cadastro no banco table: Objeto");
        PreparedStatement pStm = c.prepareStatement(sql);
        pStm.setInt(1, o.getIdConstelacao());
        pStm.setString(2, o.getObjeto());
        pStm.setString(3, o.getDesignacao());
        pStm.setString(4, o.getSignificado());
        pStm.setString(5, o.getTipo());
        pStm.setString(6, o.getVmag());
        pStm.execute();
        pStm.close();

        c.close();
    }


    @Override
    public ObservableList<Objeto> list(int id) {
        lista.clear();
        try {
            Connection c = FabricaConexao.getConnection();
            Statement stm = c.createStatement();
            ResultSet rs = stm.executeQuery("select * from Objeto where idConstelacao = "+id);
            while (rs.next()){
                Objeto objeto = monta(rs);
                lista.add(objeto);
            }
            rs.close();
            stm.close();
            c.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return lista;
    }

    private Objeto monta(ResultSet rs)throws SQLException{
        int id = rs.getInt("id");
        int idConstelacao = rs.getInt("idConstelacao");
        String objeto = rs.getString("objeto");
        String designacao = rs.getString("designacao");
        String significado = rs.getString("significado");
        String tipo = rs.getString("tipo");
        String vmag = rs.getString("vmag");

        Objeto obj = new Objeto();
        obj.setId(id);
        obj.setIdConstelacao(idConstelacao);
        obj.setObjeto(objeto);
        obj.setDesignacao(designacao);
        obj.setSignificado(significado);
        obj.setTipo(tipo);
        obj.setVmag(vmag);

        return obj;
    }

}
