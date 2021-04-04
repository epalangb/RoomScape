package src.Negocio;

public abstract class SAFactory {

    private static SAFactory instance;

    public static synchronized SAFactory getInstance() {
        if (instance == null)
            instance = new SAFactoryImp();
        return instance;
    }

    public abstract SAVideojuego crearSAVideojuego();
}
