package roomscape.es.roomscape.negocio.escape_room;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import roomscape.es.roomscape.negocio.entity.EntityEscapeRoom;
import roomscape.es.roomscape.negocio.exceptions.list.NoRoomEscapesException;
import roomscape.es.roomscape.negocio.repository.RepositoryEscapeRoom;
import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class SAEscapeRoomImpListTest {

    @Mock
    RepositoryEscapeRoom repositoryEscapeRoom;

    @InjectMocks
    SAEscapeRoomImp saEscapeRoom = new SAEscapeRoomImp();

    private EntityEscapeRoom escapeRoom;
    private List<EntityEscapeRoom> listaEscapeRooms;

    @BeforeEach
    void setMockOutput() {
        escapeRoom = new EntityEscapeRoom();
        escapeRoom.setActivo(true);
        escapeRoom.setCapacidadPersonas(10);
        escapeRoom.setDuracion(60);
        escapeRoom.setId(1);
        escapeRoom.setNombre("Escape room de prueba");
        escapeRoom.setPrecio(25);

        listaEscapeRooms = new ArrayList<>();
    }


    @Test
    @DisplayName("Comprobación de que los escape rooms se listan correctamente")
    public void listarEscapeRooms() {

        listaEscapeRooms.add(escapeRoom);

        Mockito.when(repositoryEscapeRoom.findAll()).thenReturn(listaEscapeRooms);

        try {
            List<TEscapeRoom> tEscapeRooms = saEscapeRoom.listarEscapeRooms();
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
    public void listarEscapeRoomsException() {

        Mockito.when(repositoryEscapeRoom.findAll()).thenReturn(listaEscapeRooms);

        Assertions.assertThrows(NoRoomEscapesException.class, () -> saEscapeRoom.listarEscapeRooms());
    }

    @Test
    @DisplayName("Comprobación de que el mensaje de la exepción por no tener escape rooms sea el esperado")
    public void listarEscapeRoomsExceptionMessage() {

        Mockito.when(repositoryEscapeRoom.findAll()).thenReturn(listaEscapeRooms);
        try {
            saEscapeRoom.listarEscapeRooms();
        } catch (Exception e) {
            Assertions.assertEquals(e.getMessage(), "La lista esta vacía, no existen Escape Rooms");
        }
    }
}
