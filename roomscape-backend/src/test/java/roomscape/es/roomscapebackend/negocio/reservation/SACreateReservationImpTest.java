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
import org.springframework.boot.test.context.SpringBootTest;
import roomscape.es.roomscapebackend.negocio.entity.EntityEscapeRoom;
import roomscape.es.roomscapebackend.negocio.entity.EntityReserva;
import roomscape.es.roomscapebackend.negocio.escape_room.TEscapeRoom;
import roomscape.es.roomscapebackend.negocio.exceptions.validations.*;
import roomscape.es.roomscapebackend.negocio.repository.RepositoryEscapeRoom;
import roomscape.es.roomscapebackend.negocio.repository.RepositoryReserva;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Optional;

import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class SACreateReservationImpTest {

    @Mock
    RepositoryReserva repositoryReserva;

    @Mock
    RepositoryEscapeRoom repositoryEscapeRoom;

    @InjectMocks
    SAReserva saReserva = new SAReservaImp();


    private TEscapeRoom tEscapeRoom;
    private EntityEscapeRoom entityEscapeRoomIn;

    private TReserva tReservation, tReservation2;
    private EntityReserva entityReservationIn;
    private EntityReserva entityReservationOut;

    private ArrayList<EntityReserva> reservationList;
    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

    @BeforeEach
    void setMockOutput() {

        tEscapeRoom = new TEscapeRoom();
        tEscapeRoom.setCapacidadPersonas(5);
        tEscapeRoom.setDuracion(20);
        tEscapeRoom.setNombre("Test EscapeRoom");
        tEscapeRoom.setPrecio(20);

        entityEscapeRoomIn = new EntityEscapeRoom();
        entityEscapeRoomIn.setActivo(true);
        entityEscapeRoomIn.setCapacidadPersonas(25);
        entityEscapeRoomIn.setDuracion(20);
        entityEscapeRoomIn.setNombre("Test EscapeRoom");
        entityEscapeRoomIn.setPrecio(20);

        tReservation = new TReserva();
        tReservation.setParticipantes(10);
        tReservation.setNombreEscapeRoom("Test EscapeRoom");


        tReservation.setFechaIni(sdf.format(new Calendar.Builder().setInstant(1612207200000L).build().getTime()));
        tReservation.setFechaFin(sdf.format(new Calendar.Builder().setInstant(1612208400000L).build().getTime()));

        entityReservationIn = new EntityReserva();
        entityReservationIn.setActivo(true);
        entityReservationIn.setParticipantes(10);
        entityReservationIn.setNombreEscapeRoom("Test EscapeRoom");
        entityReservationIn.setActivo(true);
        entityReservationIn.setFechaIni(new Calendar.Builder().setInstant(1612207200000L).build().getTime());
        entityReservationIn.setFechaFin(new Calendar.Builder().setInstant(1612208400000L).build().getTime());

        entityReservationOut = new EntityReserva();
        entityReservationOut.setActivo(true);
        entityReservationOut.setParticipantes(10);
        entityReservationOut.setNombreEscapeRoom("Test EscapeRoom");
        entityReservationOut.setActivo(true);
        entityReservationOut.setFechaIni(new Calendar.Builder().setInstant(1612207200000L).build().getTime());
        entityReservationOut.setFechaFin(new Calendar.Builder().setInstant(1612208400000L).build().getTime());
        entityReservationOut.setId(1);

        tReservation2 = new TReserva();
        tReservation2.setParticipantes(10);
        tReservation2.setNombreEscapeRoom("Test EscapeRoom");
        tReservation2.setFechaIni(sdf.format(new Calendar.Builder().setInstant(1612207300000L).build().getTime()));
        tReservation2.setFechaFin(sdf.format(new Calendar.Builder().setInstant(1612208500000L).build().getTime()));

        reservationList = new ArrayList<>();
    }

    @Test
    @DisplayName("Comprobación de que una reserva se da de alta correctamente")
    public void createReservation() {

        tReservation = new TReserva();
        tReservation.setParticipantes(10);
        tReservation.setNombreEscapeRoom("Test EscapeRoom");
        tReservation.setFechaIni(sdf.format(new Calendar.Builder().setInstant(1612207200000L).build().getTime()));

        entityReservationIn = new EntityReserva();
        entityReservationIn.setActivo(true);
        entityReservationIn.setParticipantes(10);
        entityReservationIn.setNombreEscapeRoom("Test EscapeRoom");
        entityReservationIn.setActivo(true);
        entityReservationIn.setFechaIni(new Calendar.Builder().setInstant(1612207200000L).build().getTime());
        entityReservationIn.setFechaFin(new Calendar.Builder().setInstant(1612208400000L).build().getTime());

        entityReservationOut = new EntityReserva();
        entityReservationOut.setActivo(true);
        entityReservationOut.setId(1);
        entityReservationOut.setParticipantes(10);
        entityReservationOut.setNombreEscapeRoom("Test EscapeRoom");
        entityReservationOut.setActivo(true);
        entityReservationOut.setFechaIni(new Calendar.Builder().setInstant(1612207200000L).build().getTime());
        entityReservationOut.setFechaFin(new Calendar.Builder().setInstant(1612208400000L).build().getTime());

        Calendar calIni = Calendar.getInstance();
        calIni.set(2021, 1, 1, 20, 20, 20);
        Calendar calFin = Calendar.getInstance();
        calFin.set(2021, 10, 10, 20, 20, 20);

        Mockito.when(repositoryEscapeRoom.findEntityEscapeRoomByNombre("Test EscapeRoom")).thenReturn(Optional.ofNullable(entityEscapeRoomIn));
        Mockito.when(repositoryReserva.findEntityReservaByNombreEscapeRoomAndFechaIni("Test EscapeRoom", new Calendar.Builder().setInstant(1612207200000L).build().getTime())).thenReturn(null);
        when(repositoryReserva.save(entityReservationIn)).thenReturn(entityReservationOut);

        try {
            TReserva testReservation = saReserva.crearReserva(tReservation);
            Assertions.assertEquals(testReservation.getId(), 1);
            Assertions.assertEquals(testReservation.getNombreEscapeRoom(), "Test EscapeRoom");
            Assertions.assertTrue(testReservation.isActivo());
            Assertions.assertEquals(testReservation.getParticipantes(), 10);
            Assertions.assertEquals(testReservation.getFechaIni(), sdf.format(new Calendar.Builder().setInstant(1612207200000L).build().getTime()));
            Assertions.assertEquals(testReservation.getFechaFin(), sdf.format(new Calendar.Builder().setInstant(1612208400000L).build().getTime()));
        } catch (Exception e) {
            Assertions.assertNull(e);
        }
    }

    @Test
    @DisplayName("Comprobación de que se lanza una exepción al intentar dar de alta una reserva con un numero invalido de participantes")
    public void CreateReservationWithInvalidNumberOfParticipants() {
        Mockito.when(repositoryEscapeRoom.findEntityEscapeRoomByNombre("Test EscapeRoom")).thenReturn(Optional.ofNullable(entityEscapeRoomIn));

        tReservation.setParticipantes(-1);
        try {
            saReserva.crearReserva(tReservation);
        } catch (Exception e) {
            Assertions.assertEquals(e.getClass(), InvalidNumberOfParticipantsException.class);
            Assertions.assertEquals(e.getMessage(), "El numero de participantes tiene que ser mayor que cero");
        }
    }

    @Test
    @DisplayName("Comprobación de que se lanza una exepción al intentar dar de alta una reserva con un numero de participantes mayor que la capacidad de la escape room")
    public void CreateReservationWithTooManyParticipants() {
        Mockito.when(repositoryEscapeRoom.findEntityEscapeRoomByNombre("Test EscapeRoom")).thenReturn(Optional.ofNullable(entityEscapeRoomIn));

        tReservation.setParticipantes(100);
        try {
            saReserva.crearReserva(tReservation);
        } catch (Exception e) {
            Assertions.assertEquals(e.getClass(), InvalidParticipantException.class);
            Assertions.assertEquals(e.getMessage(), "El numero de participantes supera la capacidad de la Escape Room");
        }
    }

    @Test
    @DisplayName("Comprobación de que se lanza una exepción al intentar dar de alta una reserva en un escape room que no existe")
    public void CreateReservationWithNonexistantEscapeRoom() {

        Mockito.when(repositoryEscapeRoom.findEntityEscapeRoomByNombre("EscapeRoom no existente")).thenReturn(Optional.ofNullable(null));
        tReservation.setNombreEscapeRoom("EscapeRoom no existente");

        try {
            saReserva.crearReserva(tReservation);
        } catch (Exception e) {
            Assertions.assertEquals(e.getClass(), InvalidEscapeRoomException.class);
            Assertions.assertEquals(e.getMessage(), "La Escape Room seleccionada no existe");
        }
    }

    @Test
    @DisplayName("Comprobación de que se lanza una exepción al intentar dar de alta una reserva que coincide con otra existente")
    public void CreateReservationOverlappingOtherReservation() {

        reservationList.add(entityReservationIn);
        Mockito.when(repositoryEscapeRoom.findEntityEscapeRoomByNombre("Test EscapeRoom")).thenReturn(Optional.ofNullable(entityEscapeRoomIn));
        Mockito.when(repositoryReserva.findEntityReservaByNombreEscapeRoom("Test EscapeRoom")).thenReturn(reservationList);

        try {
            saReserva.crearReserva(tReservation);
            saReserva.crearReserva(tReservation2);
        } catch (Exception e) {
            Assertions.assertEquals(e.getClass(), InvalidReservationOverlapsException.class);
            Assertions.assertEquals(e.getMessage(), "Ya existe una reserva para la Escape Room en el rango horario seleccionado");
        }
    }

    @Test
    @DisplayName("Comprobación de que se lanza una exepción al intentar dar de alta una reserva que coincide con otra existente")
    public void CreateReservationThatAlreadyExists() {
        Mockito.when(repositoryEscapeRoom.findEntityEscapeRoomByNombre("Test EscapeRoom")).thenReturn(Optional.ofNullable(entityEscapeRoomIn));
        Mockito.when(repositoryReserva.findEntityReservaByNombreEscapeRoomAndFechaIni("Test EscapeRoom", new Calendar.Builder().setInstant(1612207200000L).build().getTime())).thenReturn(entityReservationOut);
        try {
            saReserva.crearReserva(tReservation);
        } catch (Exception e) {
            Assertions.assertEquals(e.getClass(), InvalidReservationException.class);
            Assertions.assertEquals(e.getMessage(), "Ya existe una reserva para el Escape Room en la fecha y hora seleccionadas");
        }
    }
}