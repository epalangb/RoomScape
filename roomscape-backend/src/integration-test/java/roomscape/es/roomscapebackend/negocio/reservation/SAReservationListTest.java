package roomscape.es.roomscapebackend.negocio.reservation;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import roomscape.es.roomscapebackend.negocio.escape_room.SAEscapeRoom;
import roomscape.es.roomscapebackend.negocio.escape_room.TEscapeRoom;

import java.util.Calendar;
import java.util.List;

@SpringBootTest
public class SAReservationListTest {

    @Autowired
    SAEscapeRoom saEscapeRoom;

    @Autowired
    SAReserva saReservation;

    @Test
    @DisplayName("Check if list reservations works correctly without problems")
    public void listReservationsByDateAndHourOK() {
        TEscapeRoom escapeRoom = new TEscapeRoom();
        escapeRoom.setActivo(true);
        escapeRoom.setCapacidadPersonas(10);
        escapeRoom.setDuracion(60);
        escapeRoom.setNombre("Escape Room");
        escapeRoom.setPrecio(25);

        TReserva reservation = new TReserva();
        reservation.setActivo(true);
        reservation.setNombreEscapeRoom("Escape Room");
        reservation.setParticipantes(10);
        Calendar calIni = Calendar.getInstance();
        calIni.set(2020, 05, 22, 20, 10);
        Calendar calFin = Calendar.getInstance();
        calFin.set(2020, 05, 22, 21, 00);
        reservation.setFechaIni(calIni);
        reservation.setFechaFin(calFin);

        try {
            saEscapeRoom.createEscapeRoom(escapeRoom);
            saReservation.crearReserva(reservation);
            Calendar c = Calendar.getInstance();
            c.set(2020, 05, 21, 20, 10);
            List<TReserva> l = saReservation.listByDateAndHour(c);
            Assertions.assertEquals(l.size(), 1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
