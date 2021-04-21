package roomscape.es.roomscape.negocio.sa_factory;

import roomscape.es.roomscape.negocio.escape_room.SAEscapeRoom;

public abstract class SAFactory {

    private static SAFactory instance;

    public static synchronized SAFactory getInstance() {
        if (instance == null)
            instance = new SAFactoryImp();
        return instance;
    }

    public abstract SAEscapeRoom createSAEscapeRoom();
}
