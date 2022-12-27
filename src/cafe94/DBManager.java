package cafe94;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author ADESILE
 */


public class DBManager {
    private static final String dbHost = "localhost";
    private static final String dbname = "cafe";
    static final  String connectionString = "jdbc:mysql://" + dbHost + "/" + dbname;
    //driver and connection interface
    private static final String MYSQLJDBCDriver = "com.mysql.cj.jdbc.Driver";
    private static Connection conn = null;
    private static final String username = "root";
    private static final String password = "desile123";
    public static Connection DBConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(connectionString, username, password);
            System.out.println("Connection successful");
            return conn;
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Error occured here: " + e.getMessage());
            return null;
        }

    }
}
