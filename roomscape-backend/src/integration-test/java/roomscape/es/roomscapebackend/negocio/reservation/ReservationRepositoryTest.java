package roomscape.es.roomscapebackend.negocio.reservation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import roomscape.es.roomscapebackend.negocio.entity.EntityReserva;
import roomscape.es.roomscapebackend.negocio.repository.RepositoryReserva;

import java.util.Calendar;
import java.util.List;

@DataJpaTest
public class ReservationRepositoryTest {

    @Autowired
    RepositoryReserva repositoryReservation;

    @BeforeEach
    void init() {
        repositoryReservation.deleteAll();
    }

    @Test
    @DisplayName("Check if the database returns a reservation correctly")
    public void listReservationsOK() {

        Calendar calIni = Calendar.getInstance();
        calIni.set(2020, 05, 30, 20, 10);
        Calendar calFin = Calendar.getInstance();
        calFin.set(2020, 05, 30, 21, 00);

        EntityReserva reservation = new EntityReserva();
        reservation.setActivo(true);
        reservation.setId(1);
        reservation.setParticipantes(10);
        reservation.setNombreEscapeRoom("Escape Room");
        reservation.setFechaIni(calIni.getTime());
        reservation.setFechaFin(calFin.getTime());

        repositoryReservation.save(reservation);

        try {
            List<EntityReserva> reservationsList = repositoryReservation.findAll();
            Assertions.assertEquals(reservationsList.size(), 1);
            Assertions.assertEquals(reservationsList.get(0).isActivo(), true);
            Assertions.assertEquals(reservationsList.get(0).getParticipantes(), 10);
            Assertions.assertEquals(reservationsList.get(0).getId(), 1);
            Assertions.assertEquals(reservationsList.get(0).getNombreEscapeRoom(), "Escape Room");
            Assertions.assertEquals(reservationsList.get(0).getFechaIni().compareTo(calIni.getTime()), 0);
            Assertions.assertEquals(reservationsList.get(0).getFechaFin().compareTo(calFin.getTime()), 0);
        } catch (Exception e) {
            Assertions.assertNull(e);
        }
    }

    @Test
    @DisplayName("Check if the database does not return any reservation")
    public void listEmptyReservations() {
        try {
            List<EntityReserva> reservationsList = repositoryReservation.findAll();
            Assertions.assertTrue(reservationsList.isEmpty());
        } catch (Exception e) {
            Assertions.assertNull(e);
        }
    }

}
