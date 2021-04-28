package roomscape.es.roomscape.negocio.escape_room;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.test.context.junit4.SpringRunner;
import roomscape.es.roomscape.negocio.repository.RepositoryEscapeRoom;
import roomscape.es.roomscape.negocio.entity.EntityEscapeRoom;

@DataJpaTest
@RunWith(SpringRunner.class)
public class SAEscapeRoomAltaTest {

    @Autowired
    private RepositoryEscapeRoom repositoryEscapeRoom;

    @Test
    @DisplayName("Dar de alta un EscapeRoom de manera correcta")
    public void altaEscapeRoomExito() {
        EntityEscapeRoom entityEscapeRoom = new EntityEscapeRoom();
        entityEscapeRoom.setActivo(true);
        entityEscapeRoom.setCapacidadPersonas(5);
        entityEscapeRoom.setDuracion(20);
        entityEscapeRoom.setNombre("Test EscapeRoom");
        entityEscapeRoom.setPrecio(20);

        repositoryEscapeRoom.save(entityEscapeRoom);
        EntityEscapeRoom escapeRoom = repositoryEscapeRoom.findEntityEscapeRoomByNombre("Test EscapeRoom");

        Assertions.assertEquals(escapeRoom.getId(), 1);
        Assertions.assertEquals(escapeRoom.getNombre(), "Test EscapeRoom");
        Assertions.assertEquals(escapeRoom.getDuracion(), 20);
        Assertions.assertTrue(escapeRoom.isActivo());
        Assertions.assertEquals(escapeRoom.getPrecio(), 20);
        Assertions.assertEquals(escapeRoom.getCapacidadPersonas(), 5);
    }

    @Test(expected = InvalidDataAccessApiUsageException.class)
    @DisplayName("Dar de alta un escapeRoom con datos invalidos")
    public void altaEscapeRoom() throws InvalidDataAccessApiUsageException {
        repositoryEscapeRoom.save(null);
    }
}