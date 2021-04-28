package roomscape.es.roomscape.negocio.escape_room;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.junit.jupiter.MockitoExtension;
import roomscape.es.roomscape.negocio.entity.EntityEscapeRoom;
import roomscape.es.roomscape.negocio.repository.RepositoryEscapeRoom;
import org.junit.jupiter.api.Assertions;

import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class SAEscapeRoomImpAltaTest {

    @Mock
    RepositoryEscapeRoom repositoryEscapeRoom;

    @InjectMocks
    SAEscapeRoomImp saEscapeRoom = new SAEscapeRoomImp();


    private EntityEscapeRoom escapeRoomIn;
    private EntityEscapeRoom escapeRoomOut;

    @BeforeEach
    void setMockOutput() {
        escapeRoomIn = new EntityEscapeRoom();
        escapeRoomIn.setActivo(true);
        escapeRoomIn.setCapacidadPersonas(5);
        escapeRoomIn.setDuracion(20);
        escapeRoomIn.setNombre("Test EscapeRoom");
        escapeRoomIn.setPrecio(20);

        escapeRoomOut = new EntityEscapeRoom();
        escapeRoomOut.setId(1);
        escapeRoomOut.setActivo(true);
        escapeRoomOut.setCapacidadPersonas(5);
        escapeRoomOut.setDuracion(20);
        escapeRoomOut.setId(1);
        escapeRoomOut.setNombre("Test EscapeRoom");
        escapeRoomOut.setPrecio(20);
    }

    @Test
    @DisplayName("Alta de Escape Room")
    public void createEscapeRoom() {

        try {
        when(repositoryEscapeRoom.findEntityEscapeRoomByNombre("Test EscapeRoom")).thenReturn(null);
        when(repositoryEscapeRoom.save(escapeRoomIn)).thenReturn(escapeRoomOut);

        TEscapeRoom transferEscapeRoom = escapeRoomIn.toTransfer();
        TEscapeRoom testEscapeRoom = saEscapeRoom.crearEscapeRoom(transferEscapeRoom);
        Assertions.assertEquals(testEscapeRoom.getId(), 1);
        Assertions.assertEquals(testEscapeRoom.getNombre(), "Test EscapeRoom");
        Assertions.assertTrue(testEscapeRoom.isActivo());
        Assertions.assertEquals(testEscapeRoom.getDuracion(), 20);
        Assertions.assertEquals(testEscapeRoom.getPrecio(), 20);
        Assertions.assertEquals(testEscapeRoom.getCapacidadPersonas(), 5);
        } catch (Exception e) {
            Assertions.assertNull(e);
        }
    }
    @Test
    @DisplayName("Alta fallida de un escape room que exista en la BDD")
    public void CreateEscapeRoomAlreadyExists() {

    try{
        // mocks repositoryVideoGame
        when(repositoryEscapeRoom.findEntityEscapeRoomByNombre("Test EscapeRoom")).thenReturn(escapeRoomOut);

        TEscapeRoom transferEscapeRoom = escapeRoomIn.toTransfer();
        TEscapeRoom testEscapeRoom = saEscapeRoom.crearEscapeRoom(transferEscapeRoom);

        Assertions.assertEquals(testEscapeRoom.getId(), 1);
        Assertions.assertEquals(testEscapeRoom.getNombre(), "Test EscapeRoom");
        Assertions.assertEquals(testEscapeRoom.isActivo(), 1);
        Assertions.assertEquals(testEscapeRoom.getDuracion(), 20);
        Assertions.assertEquals(testEscapeRoom.getPrecio(), 20);
        Assertions.assertEquals(testEscapeRoom.getCapacidadPersonas(), 5);
         } catch (Exception e) {
        Assertions.assertEquals(e.getMessage(), "El nombre no es válido porque ya existe en la base de datos");
         }
    }
}