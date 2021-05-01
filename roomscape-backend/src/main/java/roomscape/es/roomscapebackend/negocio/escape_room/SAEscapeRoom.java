package roomscape.es.roomscapebackend.negocio.escape_room;

import java.util.List;

public interface SAEscapeRoom {

    TEscapeRoom createEscapeRoom(TEscapeRoom tEscapeRoom) throws Exception;

    List<TEscapeRoom> listEscapeRooms() throws Exception;
}
