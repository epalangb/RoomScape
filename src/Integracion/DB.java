package src.Integracion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DB {

    private static Connection connection;

    final static String DATABASE = "jdbc:mysql://mysql.roomscape.es:3306/roomscape_prototype";
    final static String USER = "roomscape";
    final static String PASSWORD = "roomscape";

    public static Connection getConnection() {
        try {
            connection = DriverManager.getConnection(DATABASE, USER, PASSWORD);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return connection;
    }
}
