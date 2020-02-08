package sample.Modelo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.ArrayList;

public class JDBCConstelacaoDAO implements ConstelacaoDAO {

    private static JDBCConstelacaoDAO instance;
    private ObservableList<Constelacao> lista;

    private JDBCConstelacaoDAO(){
        lista = FXCollections.observableArrayList();
    }

    public static JDBCConstelacaoDAO getInstance(){
        if(instance==null){
            instance = new JDBCConstelacaoDAO();
        }
        return instance;
    }

    @Override
    public void Create(Constelacao constelacao) throws Exception {
        Connection c = FabricaConexao.getConnection();

        String sql = "insert into" +
                " Constelacao(nome,lancado,qtdLinhas,descricao)" +
                "values(?,?,?)";

        if(constelacao.getNome().isEmpty() && constelacao.getQtdLinhas()!= 0) {
        }else{
            System.out.println("cadastro no banco table: Constelacao");
            PreparedStatement pStm = c.prepareStatement(sql);
            pStm.setString(1, constelacao.getNome());
            pStm.setInt(2, constelacao.getLancado());
            pStm.setInt(3, constelacao.getQtdLinhas());
            pStm.setString(4, constelacao.getDescricao());
            pStm.execute();
            pStm.close();
        }
        c.close();
    }


    @Override
    public ObservableList<Constelacao> List() {
        lista.clear();
        try {
            Connection c = FabricaConexao.getConnection();
            Statement stm = c.createStatement();
            ResultSet rs = stm.executeQuery("select * from Constelacao");
            while (rs.next()){
                Constelacao constelacao = montaConstelacao(rs);
                lista.add(constelacao);
            }
            rs.close();
            stm.close();
            c.close();
            return lista;
        }catch (Exception e){
            e.printStackTrace();
        }
        return lista;
    }

    @Override
    public ObservableList<Constelacao> List2() {
        lista.clear();
        try {
            Connection c = FabricaConexao.getConnection();
            Statement stm = c.createStatement();
            ResultSet rs = stm.executeQuery("select * from Constelacao where lancado = " +0);
            while (rs.next()){
                Constelacao constelacao = montaConstelacao(rs);
                lista.add(constelacao);
            }
            rs.close();
            stm.close();
            c.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return lista;
    }

    @Override
    public void Update(Constelacao constelacao){
        try{
            Connection c = FabricaConexao.getConnection();

            String desc = "update Constelacao SET descricao = '"+constelacao.getDescricao()+"' where id = "+constelacao.getId();
            String text = "update Constelacao SET texto = '"+constelacao.getTexto()+"' where id = "+constelacao.getId();
            String img1 = "update Constelacao SET img1 = '"+constelacao.getImg1()+"' where id = "+constelacao.getId();
            String img2 = "update Constelacao SET img2 = '"+constelacao.getImg2()+"' where id = "+constelacao.getId();
            String img3 = "update Constelacao SET img3 = '"+constelacao.getImg3()+"' where id = "+constelacao.getId();
            String img4 = "update Constelacao SET img4 = '"+constelacao.getImg4()+"' where id = "+constelacao.getId();

            PreparedStatement pStm1 = c.prepareStatement(desc);
            PreparedStatement pStm2 = c.prepareStatement(text);
            PreparedStatement pStm3 = c.prepareStatement(img1);
            PreparedStatement pStm4 = c.prepareStatement(img2);
            PreparedStatement pStm5 = c.prepareStatement(img3);
            PreparedStatement pStm6 = c.prepareStatement(img4);

            pStm1.execute();
            pStm2.execute();
            pStm3.execute();
            pStm4.execute();
            pStm5.execute();
            pStm6.execute();

            pStm1.close();
            pStm2.close();
            pStm3.close();
            pStm4.close();
            pStm5.close();
            pStm6.close();

            c.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void Update2(int id){
        try{
            Connection c = FabricaConexao.getConnection();

            String s = "update Constelacao SET lancado = "+1+" where id = "+ id;

            PreparedStatement pStm = c.prepareStatement(s);

            pStm.execute();

            pStm.close();

            c.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public Constelacao Search(int id) {
        //lista.clear();
        Constelacao constelacao = null;
        try{
            Connection c = FabricaConexao.getConnection();
            Statement stm = c.createStatement();
            ResultSet rs = stm.executeQuery("select * from Constelacao where id = " +id);
            while(rs.next()){
                constelacao = montaPorCatalogo(rs);
                return constelacao;
            }
        rs.close();
        stm.close();
        c.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return constelacao;
    }

    @Override
    public ArrayList<Integer> retornaFeitas() {
        ArrayList<Integer> ids = new ArrayList<Integer>();
        try {
            Connection c = FabricaConexao.getConnection();
            Statement stm = c.createStatement();
            ResultSet rs = stm.executeQuery("select * from Constelacao where lancado = 1");
            while (rs.next()){
                int id = rs.getInt("id");
                ids.add(id);
            }
            rs.close();
            stm.close();
            c.close();
            return ids;
        }catch (Exception e){
            e.printStackTrace();
        }
        return ids;
    }

    @Override
    public ObservableList<Constelacao> buscaPorCatalogo(int idCatalogo) {
        lista.clear();
        Constelacao constelacao = null;
        try {
            Connection c = FabricaConexao.getConnection();
            Statement stm = c.createStatement();
            ResultSet rs = stm.executeQuery("select * from Catalogo_has_Constelacao where idCatalogo = " +idCatalogo);
            while (rs.next()){
                int idConstelacao = rs.getInt("idConstelacao");
                constelacao = JDBCConstelacaoDAO.getInstance().Search(idConstelacao);
                lista.add(constelacao);
            }
            rs.close();
            stm.close();
            c.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return lista;
    }

    private Constelacao montaConstelacao(ResultSet rs)throws SQLException{
        int id = rs.getInt("id");
        String nome = rs.getString("nome");
        int lancado = rs.getInt("lancado");
        int qtdLinhas = rs.getInt("qtdLinhas");
        String descricao = rs.getString("descricao");
        String Texto = rs.getString("texto");
        String img1 = rs.getString("img1");
        String img2 = rs.getString("img2");
        String img3 = rs.getString("img3");
        String img4 = rs.getString("img4");

        Constelacao constelacao = new Constelacao();
        constelacao.setId(id);
        constelacao.setNome(nome);
        constelacao.setLancado(lancado);
        constelacao.setQtdLinhas(qtdLinhas);
        constelacao.setDescricao(descricao);
        constelacao.setTexto(Texto);
        constelacao.setImg1(img1);
        constelacao.setImg2(img2);
        constelacao.setImg3(img3);
        constelacao.setImg4(img4);
        return constelacao;
    }

    private Constelacao montaPorCatalogo(ResultSet rs)throws SQLException{
        int id = rs.getInt("id");
        String nome = rs.getString("nome");
        int lancado = rs.getInt("lancado");
        int qtdLinhas = rs.getInt("qtdLinhas");
        String descricao = rs.getString("descricao");
        String Texto = rs.getString("texto");
        String img1 = rs.getString("img1");
        String img2 = rs.getString("img2");
        String img3 = rs.getString("img3");
        String img4 = rs.getString("img4");

        Constelacao constelacao = new Constelacao();
        constelacao.setId(id);
        constelacao.setNome(nome);
        constelacao.setLancado(lancado);
        constelacao.setQtdLinhas(qtdLinhas);
        constelacao.setDescricao(descricao);
        constelacao.setTexto(Texto);
        constelacao.setImg1(img1);
        constelacao.setImg2(img2);
        constelacao.setImg3(img3);
        constelacao.setImg4(img4);
        rs.close();
        return constelacao;
    }

}
