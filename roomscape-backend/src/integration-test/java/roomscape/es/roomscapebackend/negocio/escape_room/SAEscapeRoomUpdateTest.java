package roomscape.es.roomscapebackend.negocio.escape_room;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SAEscapeRoomUpdateTest {

    @Autowired
    SAEscapeRoom saEscapeRoom;

    @Test
    @DisplayName("Comprobación de que un escape room se actualiza correctamente")
    public void UpdateEscapeRoom() {

        TEscapeRoom tEscapeRoom = new TEscapeRoom();
        tEscapeRoom.setActivo(true);
        tEscapeRoom.setCapacidadPersonas(5);
        tEscapeRoom.setDuracion(20);
        tEscapeRoom.setId(1);
        tEscapeRoom.setNombre("ORIGINAL_NAME");
        tEscapeRoom.setPrecio(20);

        try {
            TEscapeRoom tEscapeRoomOriginal = saEscapeRoom.createEscapeRoom(tEscapeRoom);
            Assertions.assertEquals(tEscapeRoomOriginal.getCapacidadPersonas(), 5);
            Assertions.assertEquals(tEscapeRoomOriginal.getDuracion(), 20);
            Assertions.assertEquals(tEscapeRoomOriginal.getId(), 1);
            Assertions.assertEquals(tEscapeRoomOriginal.getNombre(), "ORIGINAL_NAME");
            Assertions.assertEquals(tEscapeRoomOriginal.getPrecio(), 20);
            Assertions.assertTrue(tEscapeRoomOriginal.isActivo());

            tEscapeRoomOriginal.setCapacidadPersonas(10);
            tEscapeRoomOriginal.setDuracion(40);
            tEscapeRoomOriginal.setNombre("NEW_NAME");
            tEscapeRoomOriginal.setPrecio(40);

            TEscapeRoom tEscapeRoomSaved = saEscapeRoom.updateEscapeRoom(tEscapeRoomOriginal);
            Assertions.assertEquals(tEscapeRoomSaved.getCapacidadPersonas(), 10);
            Assertions.assertEquals(tEscapeRoomSaved.getDuracion(), 40);
            Assertions.assertEquals(tEscapeRoomSaved.getId(), 1);
            Assertions.assertEquals(tEscapeRoomSaved.getNombre(), "NEW_NAME");
            Assertions.assertEquals(tEscapeRoomSaved.getPrecio(), 40);
            Assertions.assertTrue(tEscapeRoomSaved.isActivo());
        } catch (Exception e) {
            Assertions.assertNull(e);
        }
    }
}