package roomscape.es.roomscapebackend.negocio.escape_room;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import roomscape.es.roomscapebackend.negocio.entity.EntityEscapeRoom;
import roomscape.es.roomscapebackend.negocio.entity.EntityReserva;
import roomscape.es.roomscapebackend.negocio.exceptions.validations.InvalidReservationPendingException;
import roomscape.es.roomscapebackend.negocio.exceptions.validations.NonExistentEscapeRoom;
import roomscape.es.roomscapebackend.negocio.repository.RepositoryEscapeRoom;
import roomscape.es.roomscapebackend.negocio.repository.RepositoryReserva;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class SAEscapeRoomImpDeleteTest {

    @Mock
    RepositoryEscapeRoom repositoryEscapeRoom;

    @Mock
    RepositoryReserva repositoryReserva;

    @InjectMocks
    SAEscapeRoom saEscapeRoom = new SAEscapeRoomImp();


    private TEscapeRoom tEscapeRoom;
    private EntityEscapeRoom entityEscapeRoom;
    private EntityEscapeRoom entityEscapeRoomToDelete;
    private EntityReserva entityReserva;
    private List<EntityReserva> reservations;

    private

    @BeforeEach
    void setMockOutput() {

        tEscapeRoom = new TEscapeRoom();
        tEscapeRoom.setActivo(true);
        tEscapeRoom.setCapacidadPersonas(5);
        tEscapeRoom.setDuracion(40);
        tEscapeRoom.setId(1);
        tEscapeRoom.setNombre("ESCAPE_ROOM");
        tEscapeRoom.setPrecio(40);

        entityEscapeRoom = new EntityEscapeRoom(tEscapeRoom);

        entityEscapeRoomToDelete = new EntityEscapeRoom(tEscapeRoom);
        entityEscapeRoomToDelete.setActivo(false);

        entityReserva = new EntityReserva();
        entityReserva.setId(1);
        entityReserva.setActivo(true);
        entityReserva.setNombreEscapeRoom("ESCAPE_ROOM");
        entityReserva.setParticipantes(4);
        entityReserva.setFechaIni(new Date());
        entityReserva.setFechaFin(new Date());

        reservations = new ArrayList<>();
    }

    @Test
    @DisplayName("Comprobación de que un escape room se da de baja correctamente")
    public void DeleteEscapeRoom() {

        when(repositoryEscapeRoom.findById(1)).thenReturn(Optional.ofNullable(entityEscapeRoom));
        when(repositoryReserva.findReservationsByEscapeRoomAfterDate(any(), any())).thenReturn(reservations);
        when(repositoryEscapeRoom.save(entityEscapeRoomToDelete)).thenReturn(entityEscapeRoomToDelete);

        try {
            int deletedId = saEscapeRoom.deleteEscapeRoom(1);
            Assertions.assertEquals(deletedId, 1);
        } catch (Exception e) {
            Assertions.assertNull(e);
        }
    }

    @Test
    @DisplayName("Comprobación de que se lanza una exepción al intentar dar de baja un escape room que no existe en la BDD")
    public void DeleteEscapeRoomNonExistsInBDD() {

        when(repositoryEscapeRoom.findById(1)).thenReturn(Optional.ofNullable(null));
        try {
            saEscapeRoom.deleteEscapeRoom(1);
        } catch (Exception e) {
            Assertions.assertEquals(e.getClass(), NonExistentEscapeRoom.class);
            Assertions.assertEquals(e.getMessage(), "No se puede realizar la operación porque el escape room con id: 1 no existe");
        }
    }

    @Test
    @DisplayName("Comprobación de que se lanza una exepción al intentar dar de baja un escape room con un reservas pendientes")
    public void DeleteEscapeRoomWithPendingReservations() {

        reservations.add(entityReserva);

        when(repositoryEscapeRoom.findById(1)).thenReturn(Optional.ofNullable(entityEscapeRoom));
        when(repositoryReserva.findReservationsByEscapeRoomAfterDate(any(), any())).thenReturn(reservations);

        try {
            saEscapeRoom.deleteEscapeRoom(1);
        } catch (Exception e) {
            Assertions.assertEquals(e.getClass(), InvalidReservationPendingException.class);
            Assertions.assertEquals(e.getMessage(), "No se puede dar de baja el escape room porque tiene reservas pendientes");
        }
    }
}