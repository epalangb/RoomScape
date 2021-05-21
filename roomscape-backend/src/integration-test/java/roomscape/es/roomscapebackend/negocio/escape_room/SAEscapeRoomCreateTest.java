package roomscape.es.roomscapebackend.negocio.escape_room;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class SAEscapeRoomCreateTest {

    @Autowired
    SAEscapeRoom saEscapeRoom;

    @Test
    @DisplayName("Comprobación de que un escape room se da de alta correctamente")
    public void CreateEscapeRoom() {

        TEscapeRoom tEscapeRoom = new TEscapeRoom();
        tEscapeRoom.setCapacidadPersonas(5);
        tEscapeRoom.setDuracion(20);
        tEscapeRoom.setNombre("Test EscapeRoom");
        tEscapeRoom.setPrecio(20);

        try {
            TEscapeRoom tEscapeRoomSaved = saEscapeRoom.createEscapeRoom(tEscapeRoom);
            Assertions.assertEquals(tEscapeRoomSaved.getCapacidadPersonas(), 5);
            Assertions.assertEquals(tEscapeRoomSaved.getDuracion(), 20);
            Assertions.assertEquals(tEscapeRoomSaved.getId(), 1);
            Assertions.assertEquals(tEscapeRoomSaved.getNombre(), "Test EscapeRoom");
            Assertions.assertEquals(tEscapeRoomSaved.getPrecio(), 20);
            Assertions.assertTrue(tEscapeRoomSaved.isActivo());
        } catch (Exception e) {
            Assertions.assertNull(e);
        }
    }
}