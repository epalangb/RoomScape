package roomscape.es.roomscapebackend.negocio.escape_room;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class SAEscapeRoomListTest {

    @Autowired
    SAEscapeRoom saEscapeRoom;

    @Test
    @DisplayName("Comprobación de que los escape rooms se listan correctamente")
    public void listEscapeRooms() {

        TEscapeRoom escapeRoom = new TEscapeRoom();
        escapeRoom.setActivo(true);
        escapeRoom.setCapacidadPersonas(10);
        escapeRoom.setDuracion(60);
        escapeRoom.setNombre("Escape Room");
        escapeRoom.setPrecio(25);

        try {
            saEscapeRoom.createEscapeRoom(escapeRoom);
            List<TEscapeRoom> listaEscapeRooms = saEscapeRoom.listEscapeRooms();
            Assertions.assertEquals(listaEscapeRooms.size(), 2);
        } catch (Exception e) {
            Assertions.assertNull(e);
        }
    }
}
