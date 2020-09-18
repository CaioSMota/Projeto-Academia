package JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    public Connection getConnection() {
        try {
            String server = "localhost";
            String user = "root";
            String pass = "161000";
            String nomeBanco = "acad?characterEncoding=latin1&useConfigs=maxPerformance";
            String driver = "com.mysql.jdbc.Driver";
            return DriverManager.getConnection("jdbc:mysql://" + server + "/" + nomeBanco, user, pass);

        } catch (SQLException ex) {
            System.out.println("Náo abriu conexáo JDBC");
            throw new RuntimeException(ex);
        }
    }

}
