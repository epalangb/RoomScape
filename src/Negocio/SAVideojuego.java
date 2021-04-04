package src.Negocio;

import java.util.ArrayList;

public interface SAVideojuego {

    TVideojuego altaVideojuego(TVideojuego tVideojuego) throws Exception;

    ArrayList<TVideojuego> listarVideojuegos() throws Exception;
}
