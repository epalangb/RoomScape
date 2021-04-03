import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DB {
    private static Connection connection;

    final static String DATABASE = ""; //Aquí va la url
    final static String USER = "root";
    final static String PASSWORD = "";

    public static Connection getConnection(){
        try{
            if(connection == null)
                connection = DriverManager.getConnection(DATABASE, USER, PASSWORD);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return connection;
    }
}
