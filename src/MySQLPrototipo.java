package src;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLPrototipo {

    //Base de datos llamada PrototipoVideojuego
    private static String url = "jdbc:mysql://localhost/PrototipoVideojuego?autoReconect=true&useSSL=false";

    private static String username = "root";

    private static String password = "";

    private Connection connection;

    public MySQLPrototipo() {
        try {
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            connection = null;
            e.printStackTrace();
        }
    }

    public void start() {
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void commit() {
        if(connection != null){
            try {
                connection.commit();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void rollback() {
        try {
            connection.rollback();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Object obtenerRecurso() {
        return connection;

    }
}