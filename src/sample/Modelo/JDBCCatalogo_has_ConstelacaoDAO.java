package sample.Modelo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class JDBCCatalogo_has_ConstelacaoDAO implements Catalogo_has_ConstelacaoDAO {

    private static JDBCCatalogo_has_ConstelacaoDAO instance;
    private ObservableList<Catalogo_has_Constelacao> lista;
    private Constelacao botao = null;

    private JDBCCatalogo_has_ConstelacaoDAO(){
        lista = FXCollections.observableArrayList();
    }

    public static JDBCCatalogo_has_ConstelacaoDAO getInstance(){
        if(instance==null){
            instance = new JDBCCatalogo_has_ConstelacaoDAO();
        }
        return instance;
    }

    @Override
    public void create(Catalogo_has_Constelacao CatConst) throws Exception {
        Connection c = FabricaConexao.getConnection();

        String sql = "insert into" +
                " Catalogo_has_Constelacao(idCatalogo,idConstelacao)" +
                "values(?,?)";

        System.out.println("cadastro no banco table: Catalogo_has_Constelacao");
        PreparedStatement pStm = c.prepareStatement(sql);
        pStm.setInt(1, CatConst.getCatalogo().getId());
        pStm.setInt(2, CatConst.getConstelacao().getId());
        pStm.execute();
        pStm.close();

        c.close();
    }

    public int contadorCatalogo(Catalogo obj) {
        lista.clear();
        int contador = 0;
        try {
            Connection c = FabricaConexao.getConnection();
            Statement stm = c.createStatement();
            ResultSet rs = stm.executeQuery("select * from Catalogo_has_Constelacao where IdCatalogo = " +obj.getId());
            while (rs.next()){
                contador = contador + 1;
            }
            rs.close();
            stm.close();
            c.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return contador;
    }

    public int Confere(int id, Catalogo obj) {
        int confere=0;
        try {
            Connection c = FabricaConexao.getConnection();
            Statement stm = c.createStatement();
            ResultSet rs = stm.executeQuery("select * from Catalogo_has_Constelacao where idCatalogo = " + obj.getId()+" AND idConstelacao = "+id);
            while (rs.next()){
                confere = 1;
            }
            rs.close();
            stm.close();
            c.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return confere;
    }

    public Catalogo_has_Constelacao monta(ResultSet rs) throws SQLException{
        int id = rs.getInt("id");
        int idCatalogo = rs.getInt("idCatalogo");
        int idConstelacao = rs.getInt("idConstelacao");

       Catalogo_has_Constelacao has = new Catalogo_has_Constelacao();
       has.setId(id);
       has.setCatalogo(JDBCCatalogoDAO.getInstance().Search(idCatalogo));
       has.setConstelacao(JDBCConstelacaoDAO.getInstance().Search(idConstelacao));
        return has;
    }

    public Constelacao getBotao() {
        return botao;
    }

    public void setBotao(Constelacao botao) {
        this.botao = botao;
    }
}