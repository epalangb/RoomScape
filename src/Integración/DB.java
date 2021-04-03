package src.Integración;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DB {
    private static Connection connection;

    final static String DATABASE = "jdbc:mysql.roomscape.es:3306/roomscape_prototype"; //Aquí va la url
    final static String USER = "roomscape";
    final static String PASSWORD = "roomscape";


    public static Connection getConnection(){
        try{
                connection = DriverManager.getConnection(DATABASE, USER, PASSWORD);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return connection;
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
