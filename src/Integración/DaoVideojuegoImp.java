package src.Integración;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class DaoVideojuegoImp  implements   DAOVideojuego{
    @Override
    public void altaVideojuego(TFVideojuego tfVideojuego) {
        PreparedStatement stmt;
        try {
            stmt = DB.getConnection().prepareStatement("INSERT INTO VIDEOJUEGO (nombre, descripcion, consola) VALUES (?,?,?)");

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
        return null;
    }
}
