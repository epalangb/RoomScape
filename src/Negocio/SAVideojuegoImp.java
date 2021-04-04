package src.Negocio;

import src.Integracion.DAOFactory;
import src.Integracion.DAOVideojuego;

import java.util.ArrayList;

public class SAVideojuegoImp implements SAVideojuego {
    @Override
    public TVideojuego altaVideojuego(TVideojuego tVideojuego) throws Exception {
        int id;
        DAOVideojuego daoVideojuego = DAOFactory.getInstance().crearDaoVideojuego();
        try {
            id = daoVideojuego.altaVideojuego(tVideojuego);
            tVideojuego.setId(id);
        } catch (Exception e) {
            throw new Exception("No se ha dado de alta el videojuego: " + e.getMessage());
        }
        return tVideojuego;
    }

    @Override
    public ArrayList<TVideojuego> listarVideojuegos() throws Exception {
        DAOVideojuego daoVideojuego = DAOFactory.getInstance().crearDaoVideojuego();
        ArrayList<TVideojuego> tVideojuegos;
        try {
            tVideojuegos = daoVideojuego.listarVideojuegos();
        } catch (Exception e) {
            throw new Exception("No se pueden listar los videojuegos: " + e.getMessage());
        }
        return tVideojuegos;
    }
}
