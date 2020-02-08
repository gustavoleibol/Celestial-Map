package sample.Modelo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.omg.PortableInterceptor.INACTIVE;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;

public class ControleBackup {

    private ArrayList<Usuario> suporte1;
    private static ControleBackup instance;
    private static String ARQ_USER ="Usuarios.txt";

    private ControleBackup(){
        try {
            suporte1 = new ArrayList<Usuario>();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static ControleBackup getInstance(){
        if(instance==null){
            instance = new ControleBackup();
        }
        return instance;
    }

    public void salvarTXT(String tabela) throws IOException {
        suporte1.clear();

        try {
            Connection c = FabricaConexao.getConnection();
            Statement stm = c.createStatement();
            Statement stm1 = c.createStatement();
            if (tabela == "Usuarios") {
                ResultSet rs = stm.executeQuery("select * from Usuario where categoria = 1");
                while (rs.next()) {
                    Usuario user = JDBCUsuariosDAO.getInstance().montaUsuario(rs);
                    suporte1.add(user);
                }
                rs.close();
                stm.close();
                c.close();

                FileWriter fw1 = new FileWriter(ARQ_USER);
                BufferedWriter bw1 = new BufferedWriter(fw1);

                for (Usuario u : suporte1) {
                    if (u.getCategoria() == 1) {
                        bw1.write(u.getId() + ";" +
                                u.getCatalogo().getId() + ";" +
                                u.getLogin() + ";" + u.getNome() + ";" + u.getSenha() + ";" + u.getAudio() + ";" + u.getCorLinha() + ";" + u.getCorEstrela() + ";" + u.getCategoria() + "\n");
                    }
                }

                bw1.close();
                fw1.close();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void lerTXT(String tabela) throws IOException{

        try {
            Connection c = FabricaConexao.getConnection();
            if (tabela == "Usuarios") {
                PreparedStatement stm = c.prepareStatement("delete from Usuario where categoria = 1");
                stm.execute();
                stm.close();

                FileReader fr = new FileReader(ARQ_USER);
                BufferedReader br1 = new BufferedReader(fr);

                suporte1.clear();
                String linha = null;

                while ((linha = br1.readLine()) != null) {
                    String[] tks = linha.split(";");

                    int id = Integer.parseInt(tks[0]);
                    int idCatalogo = Integer.parseInt(tks[1]);
                    String login = tks[2];
                    String nome = tks[3];
                    String senha = tks[4];
                    double audio = Double.parseDouble(tks[5]);
                    String corLinha = tks[6];
                    String corEstrela = tks[7];
                    int categoria = Integer.parseInt(tks[8]);

                    Usuario user = new Usuario();
                    user.setId(id);
                    Catalogo catalogo = JDBCCatalogoDAO.getInstance().Search(idCatalogo);
                    user.setCatalogo(catalogo);
                    user.setLogin(login);
                    user.setNome(nome);
                    user.setSenha(senha);
                    user.setAudio(audio);
                    user.setCorLinha(corLinha);
                    user.setCorEstrela(corEstrela);
                    user.setCategoria(categoria);

                    JDBCUsuariosDAO.getInstance().Create(user);
                }
                c.close();
            }
        }catch (Exception e){

        }
    }

    public ObservableList<String> retornaTabelas(){
        ObservableList<String> tabelas = FXCollections.observableArrayList();
        tabelas.add("Usuarios");
        return tabelas;
    }
}
