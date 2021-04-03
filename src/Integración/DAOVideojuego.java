package src.Integración;

import java.util.ArrayList;

public interface DAOVideojuego {

    void altaVideojuego(TFVideojuego tfVideojuego);

    ArrayList<TFVideojuego> listarVideojuegos();
}
