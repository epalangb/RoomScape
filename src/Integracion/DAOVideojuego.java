package src.Integracion;

import src.Negocio.TVideojuego;

import java.util.ArrayList;

public interface DAOVideojuego {

    int altaVideojuego(TVideojuego tVideojuego) throws Exception;

    ArrayList<TVideojuego> listarVideojuegos() throws Exception;
}
