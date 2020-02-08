package sample.Modelo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.Random;

public class JDBCEstrelaDAO implements EstrelaDAO {

    private static JDBCEstrelaDAO instance;
    private ObservableList<Estrela> lista;

    private JDBCEstrelaDAO() {
        lista = FXCollections.observableArrayList();
    }

    public static JDBCEstrelaDAO getInstance() {
        if (instance == null) {
            instance = new JDBCEstrelaDAO();
        }
        return instance;
    }

    @Override
    public void create(Estrela estrela) throws Exception {
        Connection c = FabricaConexao.getConnection();

        String sql = "insert into" +
                " Estrela(id,idConstelacao,x,y)" +
                "values(?,?,?,?)";

        System.out.println("cadastro no banco table:Estrela");
        PreparedStatement pStm = c.prepareStatement(sql);
        pStm.setInt(1,estrela.getId());
        pStm.setInt(2, estrela.getIdConstelacao());
        pStm.setDouble(3, estrela.getX());
        pStm.setDouble(4, estrela.getY());
        pStm.execute();
        pStm.close();
        c.close();
    }

    @Override
    public int list() {
        lista.clear();
        Estrela estrela = null;
        int tamanho=0;
        try {
            Connection c = FabricaConexao.getConnection();
            Statement stm = c.createStatement();
            ResultSet rs = stm.executeQuery("select * from Estrela order by id desc limit 1");
            while (rs.next()) {
                estrela = monta(rs);
            }
            rs.close();
            stm.close();
            c.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(estrela == null){
            tamanho = 0;
        }else{
            tamanho = estrela.getId();
        }
        return tamanho;
    }

    public ObservableList<Estrela> estrelas_de_Certa_Constelacao(int aux) {
        lista.clear();
        try {
            Connection c = FabricaConexao.getConnection();
            Statement stm = c.createStatement();
            ResultSet rs = stm.executeQuery("select * from Estrela where idConstelacao =" + aux);
            while (rs.next()) {
                Estrela estrela = monta(rs);
                lista.add(estrela);
            }
            rs.close();
            stm.close();
            c.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public Estrela monta(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        int idConstelacao = rs.getInt("idConstelacao");
        double x = rs.getDouble("x");
        double y = rs.getDouble("y");

        Estrela estrela = new Estrela();
        estrela.setId(id);
        estrela.setIdConstelacao(idConstelacao);
        estrela.setX(x);
        estrela.setY(y);

        return estrela;
    }

    @Override
    public void clear(Estrela estrela) {
    }
}
