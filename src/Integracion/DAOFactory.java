package src.Integracion;

public abstract class DAOFactory {

    private static DAOFactory instance;

    public static synchronized DAOFactory getInstance() {
        if (instance == null)
            instance = new DAOFactoryImp();
        return instance;
    }

    public abstract DAOVideojuego crearDaoVideojuego();
}
