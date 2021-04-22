package roomscape.es.roomscape.negocio.escape_room;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import roomscape.es.roomscape.negocio.entity.EntityEscapeRoom;
import roomscape.es.roomscape.negocio.repository.RepositoryEscapeRoom;

import java.util.List;

@DataJpaTest
public class RepositoryEscapeRoomTest {

    @Autowired
    RepositoryEscapeRoom repositoryEscapeRoom;

    @BeforeEach
    void init() {
        repositoryEscapeRoom.deleteAll();
    }


    @Test
    @DisplayName("Comprobación de que la base de datos devuelve la lista de escape rooms")
    public void listarEscapeRoomsSinResultados() {

        EntityEscapeRoom escapeRoom = new EntityEscapeRoom();
        escapeRoom.setActivo(true);
        escapeRoom.setId(1);
        escapeRoom.setCapacidadPersonas(10);
        escapeRoom.setDuracion(60);
        escapeRoom.setNombre("Escape Room");
        escapeRoom.setPrecio(25);

        repositoryEscapeRoom.save(escapeRoom);

        try {
            List<EntityEscapeRoom> listaEscapeRooms = repositoryEscapeRoom.findAll();
            Assertions.assertEquals(listaEscapeRooms.size(), 1);
            Assertions.assertEquals(listaEscapeRooms.get(0).isActivo(), true);
            Assertions.assertEquals(listaEscapeRooms.get(0).getCapacidadPersonas(), 10);
            Assertions.assertEquals(listaEscapeRooms.get(0).getDuracion(), 60);
            Assertions.assertEquals(listaEscapeRooms.get(0).getId(), 1);
            Assertions.assertEquals(listaEscapeRooms.get(0).getNombre(), "Escape Room");
            Assertions.assertEquals(listaEscapeRooms.get(0).getPrecio(), 25);
        } catch (Exception e) {
            Assertions.assertNull(e);
        }
    }

    @Test
    @DisplayName("Comprobación de que la base de datos devuelve una lista vacía de escape rooms")
    public void listarEscapeRoomsConUnEscapeRoom() {

        try {
            List<EntityEscapeRoom> listaEscapeRooms = repositoryEscapeRoom.findAll();
            Assertions.assertEquals(listaEscapeRooms.size(), 0);
        } catch (Exception e) {
            Assertions.assertNull(e);
        }
    }
}
