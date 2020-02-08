package sample.Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class
FabricaConexao {

    private static Connection c;
    private static String nome = "info17_gustavo";
    private static String senha = "0192";

    public static Connection getConnection() throws SQLException {
        if((c==null) || (c.isClosed())){
            c = DriverManager.getConnection("jdbc:mysql://infoprojetos.com.br:3132/info17_gustavo?useTimezone=true&serverTimezone=UTC", nome, senha);
        }
        return c;
    }
}
