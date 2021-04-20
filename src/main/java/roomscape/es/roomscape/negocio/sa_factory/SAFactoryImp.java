package roomscape.es.roomscape.negocio.sa_factory;

import roomscape.es.roomscape.negocio.escape_room.SAEscapeRoom;
import roomscape.es.roomscape.negocio.escape_room.SAEscapeRoomImp;

public class SAFactoryImp extends SAFactory {

    @Override
    public SAEscapeRoom createSAEscapeRoom() {
        return new SAEscapeRoomImp();
    }
}
