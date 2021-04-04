package src.Integracion;

import src.Negocio.TVideojuego;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DaoVideojuegoImp implements DAOVideojuego {

    @Override
    public int altaVideojuego(TVideojuego tVideojuego) throws Exception {
        PreparedStatement stmt;
        int id;
        try {
            stmt = DB.getConnection().prepareStatement("INSERT INTO entity_video_game (name, description, console) VALUES (?,?,?)");

            stmt.setString(1, tVideojuego.getNombre());
            stmt.setString(2, tVideojuego.getDescripcion());
            stmt.setString(3, tVideojuego.getConsola());
            id = stmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new Exception("Ha ocurrido un error al intentar dar de alta el videojuego: " + tVideojuego.getNombre());
        }
        return id;
    }

    @Override
    public ArrayList<TVideojuego> listarVideojuegos() throws Exception {
        PreparedStatement stmt;
        ArrayList<TVideojuego> tVideojuegos = new ArrayList<TVideojuego>();
        try {
            stmt = DB.getConnection().prepareStatement("SELECT * FROM entity_video_game");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                TVideojuego tVideojuego = new TVideojuego();

                tVideojuego.setDescripcion(rs.getString(2));
                tVideojuego.setNombre(rs.getString(3));
                tVideojuego.setConsola(rs.getString(4));
                tVideojuegos.add(tVideojuego);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new Exception("Ha ocurrido un error al intentar recuperar la lista de videojuegos");
        }
        return tVideojuegos;
    }
}
