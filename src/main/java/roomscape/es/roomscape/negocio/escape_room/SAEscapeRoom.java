package roomscape.es.roomscape.negocio.escape_room;

import java.util.List;

public interface SAEscapeRoom {
    public TEscapeRoom crearEscapeRoom(TEscapeRoom tEscapeRoom) throws Exception;
    public List<TEscapeRoom> listarEscapeRooms() throws Exception;
}
