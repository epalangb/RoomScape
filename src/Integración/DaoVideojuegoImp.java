package src.Integración;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DaoVideojuegoImp  implements   DAOVideojuego{
    @Override
    public void altaVideojuego(TFVideojuego tfVideojuego) {
        PreparedStatement stmt;
        try {
            stmt = DB.getConnection().prepareStatement("INSERT INTO entity_video_game (name, description, console) VALUES (?,?,?)");

            stmt.setString(1,tfVideojuego.getNombre());
            stmt.setString(2,tfVideojuego.getDescripcion());
            stmt.setString(3,tfVideojuego.getConsola());
            stmt.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public ArrayList<TFVideojuego> listarVideojuegos() {
        PreparedStatement stmt;
        ArrayList<TFVideojuego> tflista = new ArrayList<TFVideojuego>();
        try {
            stmt = DB.getConnection().prepareStatement("SELECT  * FROM entity_video_game ");
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                TFVideojuego tf = new TFVideojuego();

                tf.setDescripcion(rs.getString(2));
                tf.setNombre(rs.getString(3));
                tf.setConsola(rs.getString(4));
                tflista.add(tf);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return tflista;
    }
}
