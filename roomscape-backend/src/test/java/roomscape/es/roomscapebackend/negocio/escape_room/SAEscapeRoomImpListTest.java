package roomscape.es.roomscapebackend.negocio.escape_room;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import roomscape.es.roomscapebackend.negocio.entity.EntityEscapeRoom;
import roomscape.es.roomscapebackend.negocio.exceptions.list.NoRoomEscapesException;
import roomscape.es.roomscapebackend.negocio.repository.RepositoryEscapeRoom;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class SAEscapeRoomImpListTest {

    @Mock
    RepositoryEscapeRoom repositoryEscapeRoom;

    @InjectMocks
    SAEscapeRoomImp saEscapeRoom = new SAEscapeRoomImp();

    private EntityEscapeRoom escapeRoom;
    private List<EntityEscapeRoom> escapeRoomList;

    @BeforeEach
    void setMockOutput() {
        escapeRoom = new EntityEscapeRoom();
        escapeRoom.setActivo(true);
        escapeRoom.setCapacidadPersonas(10);
        escapeRoom.setDuracion(60);
        escapeRoom.setId(1);
        escapeRoom.setNombre("Escape room de prueba");
        escapeRoom.setPrecio(25);

        escapeRoomList = new ArrayList<>();
    }


    @Test
    @DisplayName("Comprobación de que los escape rooms se listan correctamente")
    public void listEscapeRooms() {

        escapeRoomList.add(escapeRoom);

        Mockito.when(repositoryEscapeRoom.findAll()).thenReturn(escapeRoomList);

        try {
            List<TEscapeRoom> tEscapeRooms = saEscapeRoom.listEscapeRooms();
            Assertions.assertEquals(tEscapeRooms.size(), 1);
            Assertions.assertEquals(tEscapeRooms.get(0).isActivo(), true);
            Assertions.assertEquals(tEscapeRooms.get(0).getCapacidadPersonas(), 10);
            Assertions.assertEquals(tEscapeRooms.get(0).getDuracion(), 60);
            Assertions.assertEquals(tEscapeRooms.get(0).getId(), 1);
            Assertions.assertEquals(tEscapeRooms.get(0).getNombre(), "Escape room de prueba");
            Assertions.assertEquals(tEscapeRooms.get(0).getPrecio(), 25);
        } catch (Exception e) {
            Assertions.assertNull(e);
        }
    }

    @Test
    @DisplayName("Comprobación de que se lanza una exepción al no existir escape rooms")
    public void listEscapeRoomsException() {

        Mockito.when(repositoryEscapeRoom.findAll()).thenReturn(escapeRoomList);

        Assertions.assertThrows(NoRoomEscapesException.class, () -> saEscapeRoom.listEscapeRooms());
    }

    @Test
    @DisplayName("Comprobación de que el mensaje de la exepción por no tener escape rooms sea el esperado")
    public void listEscapeRoomsExceptionMessage() {

        Mockito.when(repositoryEscapeRoom.findAll()).thenReturn(escapeRoomList);
        try {
            saEscapeRoom.listEscapeRooms();
        } catch (Exception e) {
            Assertions.assertEquals(e.getMessage(), "La lista esta vacía, no existen Escape Rooms");
        }
    }
}