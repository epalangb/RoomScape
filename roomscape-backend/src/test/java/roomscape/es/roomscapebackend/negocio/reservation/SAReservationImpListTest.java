package roomscape.es.roomscapebackend.negocio.reservation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import roomscape.es.roomscapebackend.negocio.entity.EntityReserva;
import roomscape.es.roomscapebackend.negocio.exceptions.list.NoReservationsAvailableException;
import roomscape.es.roomscapebackend.negocio.repository.RepositoryReserva;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class SAReservationImpListTest {
    @Mock
    RepositoryReserva repositoryReservation;

    @InjectMocks
    SAReserva saReservation = new SAReservaImp();
    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");


    private EntityReserva reservation;
    private List<EntityReserva> reservationList;
    private Calendar calIni, calFin;

    @BeforeEach
    void setMockOutput() {
        reservation = new EntityReserva();
        reservation.setActivo(true);
        reservation.setParticipantes(10);
        reservation.setId(1);
        reservation.setNombreEscapeRoom("Escape room de prueba");
        calIni = Calendar.getInstance();
        calIni.setTime(new Calendar.Builder().setInstant(1612207200000L).build().getTime());
        calFin = Calendar.getInstance();
        calFin.setTime(new Calendar.Builder().setInstant(1612208400000L).build().getTime());
        reservation.setFechaIni(new Calendar.Builder().setInstant(1612207200000L).build().getTime());
        reservation.setFechaFin(new Calendar.Builder().setInstant(1612208400000L).build().getTime());

        reservationList = new ArrayList<>();
    }

    @Test
    @DisplayName("Check that reservations are listed correctly")
    public void listReservationsCorrectly() {

        reservationList.add(reservation);

        Mockito.when(repositoryReservation.findAll()).thenReturn(reservationList);

        try {
            List<TReserva> reservations = saReservation.listByDateAndHour(calIni);
            Assertions.assertEquals(reservations.size(), 1);
            Assertions.assertEquals(reservations.get(0).isActivo(), true);
            Assertions.assertEquals(reservations.get(0).getParticipantes(), 10);
            Assertions.assertEquals(reservations.get(0).getId(), 1);
            Assertions.assertEquals(reservations.get(0).getNombreEscapeRoom(), "Escape room de prueba");
            Assertions.assertEquals(reservation.getFechaIni(), new Calendar.Builder().setInstant(1612207200000L).build().getTime());
            Assertions.assertEquals(reservation.getFechaFin(), new Calendar.Builder().setInstant(1612208400000L).build().getTime());
        } catch (Exception e) {
            Assertions.assertNull(e);
        }

    }

    @Test
    @DisplayName("Check that the reservation list is ordered by date")
    public void listReservationsOrdered() {

        EntityReserva reservation2 = new EntityReserva();
        reservation2.setActivo(true);
        reservation2.setParticipantes(10);
        reservation2.setId(1);
        reservation2.setNombreEscapeRoom("Escape room de prueba");
        Calendar c1 = Calendar.getInstance();
        c1.setTime(new Calendar.Builder().setInstant(1612207210000L).build().getTime());
        Calendar c2 = Calendar.getInstance();
        c2.setTime(new Calendar.Builder().setInstant(1612208410000L).build().getTime());
        reservation2.setFechaIni(c1.getTime());
        reservation2.setFechaFin(c2.getTime());

        reservationList.add(reservation);
        reservationList.add(reservation2);

        Mockito.when(repositoryReservation.findAll()).thenReturn(reservationList);

        try {
            List<TReserva> reservations = saReservation.listByDateAndHour(calIni);
            Assertions.assertEquals(reservations.size(), 2);
            Assertions.assertEquals(reservations.get(0).getFechaIni().compareTo(sdf.format(new Calendar.Builder().setInstant(1612207200000L).build().getTime())), 0);
            Assertions.assertEquals(reservations.get(0).getFechaFin().compareTo(sdf.format(new Calendar.Builder().setInstant(1612208400000L).build().getTime())), 0);
            Assertions.assertEquals(reservations.get(1).getFechaIni().compareTo(sdf.format(new Calendar.Builder().setInstant(1612207210000L).build().getTime())), 0);
            Assertions.assertEquals(reservations.get(1).getFechaFin().compareTo(sdf.format(new Calendar.Builder().setInstant(1612208410000L).build().getTime())), 0);
        } catch (Exception e) {
            Assertions.assertNull(e);
        }

    }

    @Test
    @DisplayName("Check that if there is no reservation it throws an exception")
    public void listReservationsNotFoundReservationsByDate() {

        reservationList.add(reservation);

        Mockito.when(repositoryReservation.findAll()).thenReturn(reservationList);

        Calendar c1 = Calendar.getInstance();
        c1.setTime(new Calendar.Builder().setInstant(1612207210000L).build().getTime());
        Assertions.assertThrows(NoReservationsAvailableException.class, () -> saReservation.listByDateAndHour(c1));

    }

    @Test
    @DisplayName("Check that if there is no reservation it throws an exception")
    public void listReservationsException() {

        Mockito.when(repositoryReservation.findAll()).thenReturn(reservationList);

        Assertions.assertThrows(NoReservationsAvailableException.class, () -> saReservation.listByDateAndHour(calIni));

    }

    @Test
    @DisplayName("Verify that the exception message is correct")
    public void listReservationsExceptionWithMessage() {

        Mockito.when(repositoryReservation.findAll()).thenReturn(reservationList);

        try {
            saReservation.listByDateAndHour(calIni);
        } catch (Exception e) {
            Assertions.assertEquals(e.getMessage(), "There are no reservations available at this time");
        }

    }
}
