package src.Integracion;

public class DAOFactoryImp extends DAOFactory {
    @Override
    public DAOVideojuego crearDaoVideojuego() {
        return new DaoVideojuegoImp();
    }
}
