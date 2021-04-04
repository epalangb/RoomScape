package src.Negocio;

public class SAFactoryImp extends SAFactory {
    public SAVideojuego crearSAVideojuego() {
        return new SAVideojuegoImp();
    }
}
