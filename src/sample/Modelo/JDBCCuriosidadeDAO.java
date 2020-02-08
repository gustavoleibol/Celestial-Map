package sample.Modelo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.Random;

public class JDBCCuriosidadeDAO implements CuriosidadeDAO {

    private static JDBCCuriosidadeDAO instance;
    private ObservableList<Curiosidade> lista;

    private JDBCCuriosidadeDAO(){
        lista = FXCollections.observableArrayList();
    }

    public static JDBCCuriosidadeDAO getInstance(){
        if(instance==null){
            instance = new JDBCCuriosidadeDAO();
        }
        return instance;
    }

    @Override
    public ObservableList<Curiosidade> list() {
        lista.clear();
        try {
            Connection c = FabricaConexao.getConnection();
            Statement stm = c.createStatement();
            ResultSet rs = stm.executeQuery("select * from Curiosidade");
            while (rs.next()){
                Curiosidade curiosidade = monta(rs);
                lista.add(curiosidade);
            }
            rs.close();
            stm.close();
            c.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return lista;
    }
    Curiosidade curio;
    public Curiosidade list2() {
        lista.clear();
        Random rd = new Random();
        int aux = rd.nextInt(19) + 1;

        try {
            Connection c = FabricaConexao.getConnection();
            Statement stm = c.createStatement();
            ResultSet rs = stm.executeQuery("select * from Curiosidade where id = " +aux);
            while (rs.next()) {
                curio = monta(rs);
            }
            rs.close();
            stm.close();
            c.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return curio;
    }

    private Curiosidade monta(ResultSet rs)throws SQLException{
        int id = rs.getInt("id");
        String titulo = rs.getString("titulo");
        String descricao = rs.getString("descricao");

        Curiosidade curiosidade = new Curiosidade();
        curiosidade.setId(id);
        curiosidade.setTitulo(titulo);
        curiosidade.setDescricao(descricao);
        return curiosidade;
    }

    @Override
    public void clear(Curiosidade curiosidade){

    }
}
