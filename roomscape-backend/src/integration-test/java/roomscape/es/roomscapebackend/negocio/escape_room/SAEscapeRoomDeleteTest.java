package roomscape.es.roomscapebackend.negocio.escape_room;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import roomscape.es.roomscapebackend.negocio.reservation.SAReserva;
import roomscape.es.roomscapebackend.negocio.reservation.TReserva;

import java.util.Calendar;
import java.util.List;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class SAEscapeRoomDeleteTest {

    @Autowired
    SAEscapeRoom saEscapeRoom;

    @Autowired
    SAReserva saReserva;

    @Test
    @DisplayName("Comprobación de que un escape room se da de baja correctamente")
    public void DeleteEscapeRoom() {

        TEscapeRoom tEscapeRoom = new TEscapeRoom();
        tEscapeRoom.setActivo(true);
        tEscapeRoom.setCapacidadPersonas(5);
        tEscapeRoom.setDuracion(20);
        tEscapeRoom.setId(1);
        tEscapeRoom.setNombre("ORIGINAL_NAME");
        tEscapeRoom.setPrecio(20);

        TReserva tReserva = new TReserva();
        tReserva.setNombreEscapeRoom("ORIGINAL_NAME");
        tReserva.setParticipantes(4);
        tReserva.setFechaIni(new Calendar.Builder().setInstant(1612207200000L).build().toString());
        tReserva.setFechaFin(new Calendar.Builder().setInstant(1612208400000L).build().toString());

        try {
            saEscapeRoom.createEscapeRoom(tEscapeRoom);
            saReserva.crearReserva(tReserva);

            int removedId = saEscapeRoom.deleteEscapeRoom(1);
            Assertions.assertEquals(removedId, 1);

            List<TEscapeRoom> escapeRooms = saEscapeRoom.listEscapeRooms();
            Assertions.assertEquals(escapeRooms.get(0).getId(), 1);
            Assertions.assertFalse(escapeRooms.get(0).isActivo());
        } catch (Exception e) {
            Assertions.assertNull(e);
        }
    }
}