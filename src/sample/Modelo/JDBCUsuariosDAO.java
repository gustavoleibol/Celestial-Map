package sample.Modelo;

import com.mysql.cj.exceptions.CJCommunicationsException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.net.UnknownHostException;
import java.sql.*;

public class JDBCUsuariosDAO implements UsuarioDAO {

    private static JDBCUsuariosDAO instance;
    private ObservableList<Usuario> lista;
    private static Usuario logado=null;
    private int hubble;

    private JDBCUsuariosDAO(){
        lista = FXCollections.observableArrayList();
    }

    public static JDBCUsuariosDAO getInstance(){
        if(instance==null){
            instance = new JDBCUsuariosDAO();
        }
        return instance;
    }

    @Override
    public void Create(Usuario u) throws Exception {
        Connection c = FabricaConexao.getConnection();
        String sql = "insert into Usuario(idCatalogo,login,nome,senha,audio,corLinha,corEstrela,categoria)values(?,?,?,?,?,?,?,?)";

        if(u.getLogin().isEmpty() && u.getNome().isEmpty() && u.getSenha().isEmpty()) { }
        else{
            System.out.println("cadastro no banco table:Usuario");
            PreparedStatement pStm = c.prepareStatement(sql);
            pStm.setInt(1,u.getCatalogo().getId());
            pStm.setString(2, u.getLogin());
            pStm.setString(3, u.getNome());
            pStm.setString(4, u.getSenha());
            pStm.setDouble(5, u.getAudio());
            pStm.setString(6, u.getCorLinha());
            pStm.setString(7, u.getCorEstrela());
            pStm.setInt(8,u.getCategoria());
            pStm.execute();
            pStm.close();
        }
        c.close();
    }

    //retorna todos os usuarios
    @Override
    public ObservableList<Usuario> List() {
        lista.clear();
        try {
            Connection c = FabricaConexao.getConnection();
            Statement stm = c.createStatement();
            ResultSet rs = stm.executeQuery("select * from Usuario where categoria = 1");
            while(rs.next()){
                Usuario u = montaUsuario(rs);
                lista.add(u);
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
    public ObservableList<Usuario> Search(String busca){//buscar pode ser feita por nome ou login
        lista.clear();
        String a1 = busca;

        if(a1 != null) {
            try {
                Connection c = FabricaConexao.getConnection();
                Statement stm = c.createStatement();
                ResultSet rs = stm.executeQuery("select * from Usuario where login LIKE '" +a1+"%' OR nome LIKE '" +a1+"%'");
                while (rs.next()){
                    Usuario u = montaUsuario(rs);
                    lista.add(u);
                }
                rs.close();
                stm.close();
                c.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return lista;
        }
        return lista;
    }

    @Override
    public void Update(Usuario u){
        try{
            Connection c = FabricaConexao.getConnection();

            String s = "update Usuario SET login = '"+u.getLogin()+"' where id = "+u.getId();
            String x = "update Usuario SET nome = '"+u.getNome()+"' where id = "+u.getId();
            String z = "update Usuario SET senha = '"+u.getSenha()+"' where id = "+u.getId();

            PreparedStatement pStm = c.prepareStatement(s);
            PreparedStatement pStm1 = c.prepareStatement(x);
            PreparedStatement pStm2 = c.prepareStatement(z);

            pStm.execute();
            pStm1.execute();
            pStm2.execute();

            pStm.close();
            pStm1.close();
            pStm2.close();

            c.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void Update2(Usuario u){
        try{
            Connection c = FabricaConexao.getConnection();

            String s = "update Usuario SET login = '"+u.getLogin()+"' where id = "+u.getId();
            String x = "update Usuario SET nome = '"+u.getNome()+"' where id = "+u.getId();
            String z = "update Usuario SET senha = '"+u.getSenha()+"' where id = "+u.getId();
            String e = "update Usuario SET audio = '"+u.getAudio()+"' where id = "+u.getId();
            String f = "update Usuario SET corLinha = '"+u.getCorLinha()+"' where id = "+u.getId();
            String g = "update Usuario SET corEstrela = '"+u.getCorEstrela()+"' where id = "+u.getId();

            PreparedStatement pStm = c.prepareStatement(s);
            PreparedStatement pStm1 = c.prepareStatement(x);
            PreparedStatement pStm2 = c.prepareStatement(z);
            PreparedStatement pStm3 = c.prepareStatement(e);
            PreparedStatement pStm4 = c.prepareStatement(f);
            PreparedStatement pStm5 = c.prepareStatement(g);

            pStm.execute();
            pStm1.execute();
            pStm2.execute();
            pStm3.execute();
            pStm4.execute();
            pStm5.execute();

            pStm.close();
            pStm1.close();
            pStm2.close();
            pStm3.close();
            pStm4.close();
            pStm5.close();

            c.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void Clear(Usuario u){
        try {
            Connection c = FabricaConexao.getConnection();
            Statement stmt = c.createStatement();
            String s = "Delete * from Usuario where id = " + u.getId();
            stmt.execute(s);

            stmt.close();
            c.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public Usuario logar(String login, String senha){
        lista.clear();
        Usuario user = null;
        if(login != null && senha != null) {
            try {
                Connection c = FabricaConexao.getConnection();
                Statement stm = c.createStatement();
                ResultSet rs = stm.executeQuery("Call VerificaLogin('"+login+"','"+senha+"')");
                //ResultSet rs = stm.executeQuery("Select * from Usuario where login = "+login+" AND senha = "+senha);
                while (rs.next()) {
                    Usuario u = montaUsuario(rs);
                    user = u;
                    return user;
                }
                rs.close();
                stm.close();
                c.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        return user;
    }

    //Select * from Usuario u where u.login like login and u.senha like descriptSenha(senha);

    public Usuario montaUsuario(ResultSet rs)throws SQLException{
        Usuario u=null;
        try {
            int id = rs.getInt("id");
            int idCatalogo = rs.getInt("idCatalogo");
            String login = rs.getString("login");
            String nome = rs.getString("nome");
            String senha = rs.getString("senha");
            double audio = rs.getDouble("audio");
            String corLinha = rs.getString("corLinha");
            String corEstrela = rs.getString("corEstrela");
            int categoria = rs.getInt("categoria");
            Catalogo catalogo = JDBCCatalogoDAO.getInstance().Search(idCatalogo);

            u = new Usuario();
            u.setId(id);
            u.setLogin(login);
            u.setNome(nome);
            u.setSenha(senha);
            u.setAudio(audio);
            u.setCorLinha(corLinha);
            u.setCorEstrela(corEstrela);
            u.setCategoria(categoria);
            u.setCatalogo(catalogo);
            return u;
        }catch(SQLException e){
            e.printStackTrace();
        }
        return u;
    }

    public Usuario getLogado(){
        return logado;
    }

    public void logout(){
        logado=null;
    }

    public void setLogado(Usuario user){ this.logado = user; }

    public int getHubble() {
        return hubble;
    }

    public void setHubble(int h) {
        this.hubble = h;
    }
}
