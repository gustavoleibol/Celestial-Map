package sample.Modelo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class JDBCLinhaDAO implements LinhaDAO {

    private static JDBCLinhaDAO instance;
    private ObservableList<Linha> lista;

    private JDBCLinhaDAO(){
        lista = FXCollections.observableArrayList();
    }

    public static JDBCLinhaDAO getInstance(){
        if(instance==null){
            instance = new JDBCLinhaDAO();
        }
        return instance;
    }

    @Override
    public void create(Linha l) throws Exception {
        Connection c = FabricaConexao.getConnection();

        String sql = "insert into" +
                " Linha(idConstelacao,idEstrela1,idEstrela2)" +
                "values(?,?,?)";

        System.out.println("cadastro no banco table: Linha");
        PreparedStatement pStm = c.prepareStatement(sql);
        pStm.setInt(1, l.getIdConstelacao());
        pStm.setInt(2,l.getIdEstrela1());
        pStm.setInt(3,l.getIdEstrela2());
        pStm.execute();
        pStm.close();
        c.close();

    }

    @Override
    public ObservableList<Linha> verifica(int a) {
        lista.clear();
        try {
            Connection c = FabricaConexao.getConnection();
            Statement stm = c.createStatement();
            ResultSet rs = stm.executeQuery("select * from Linha where idConstelacao = " +a);

            while (rs.next()){
               Linha linha = monta(rs);
               lista.add(linha);
            }
            rs.close();
            stm.close();
            c.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return lista;
    }

    public Linha monta(ResultSet rs)throws SQLException{
        int id = rs.getInt("id");
        int idConstelacao = rs.getInt("idConstelacao");
        int idEstrela1 = rs.getInt("idEstrela1");
        int idEstrela2 = rs.getInt("idEstrela2");

        Linha linha = new Linha();
        linha.setId(id);
        linha.setIdConstelacao(idConstelacao);
        linha.setIdEstrela1(idEstrela1);
        linha.setIdEstrela2(idEstrela2);
        return linha;
    }

    @Override
    public void clear(Linha linha) {
    }
}
