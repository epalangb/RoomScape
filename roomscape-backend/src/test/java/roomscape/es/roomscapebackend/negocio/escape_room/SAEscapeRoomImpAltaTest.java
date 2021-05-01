package roomscape.es.roomscapebackend.negocio.escape_room;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.junit.jupiter.MockitoExtension;
import roomscape.es.roomscapebackend.negocio.entity.EntityEscapeRoom;
import roomscape.es.roomscapebackend.negocio.exceptions.validations.*;
import roomscape.es.roomscapebackend.negocio.repository.RepositoryEscapeRoom;
import org.junit.jupiter.api.Assertions;

import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class SAEscapeRoomImpAltaTest {

    @Mock
    RepositoryEscapeRoom repositoryEscapeRoom;

    @InjectMocks
    SAEscapeRoom saEscapeRoom = new SAEscapeRoomImp();


    private TEscapeRoom tEscapeRoom;
    private EntityEscapeRoom entityEscapeRoomIn;
    private EntityEscapeRoom entityEscapeRoomOut;

    @BeforeEach
    void setMockOutput() {
        tEscapeRoom = new TEscapeRoom();
        tEscapeRoom.setCapacidadPersonas(5);
        tEscapeRoom.setDuracion(20);
        tEscapeRoom.setNombre("Test EscapeRoom");
        tEscapeRoom.setPrecio(20);

        entityEscapeRoomIn = new EntityEscapeRoom();
        entityEscapeRoomIn.setActivo(true);
        entityEscapeRoomIn.setCapacidadPersonas(5);
        entityEscapeRoomIn.setDuracion(20);
        entityEscapeRoomIn.setNombre("Test EscapeRoom");
        entityEscapeRoomIn.setPrecio(20);

        entityEscapeRoomOut = new EntityEscapeRoom();
        entityEscapeRoomOut.setActivo(true);
        entityEscapeRoomOut.setCapacidadPersonas(5);
        entityEscapeRoomOut.setDuracion(20);
        entityEscapeRoomOut.setId(1);
        entityEscapeRoomOut.setNombre("Test EscapeRoom");
        entityEscapeRoomOut.setPrecio(20);
    }

    @Test
    @DisplayName("Comprobación de que un escape room se da de alta correctamente")
    public void createEscapeRoom() {

        when(repositoryEscapeRoom.findEntityEscapeRoomByNombre("Test EscapeRoom")).thenReturn(null);
        when(repositoryEscapeRoom.save(entityEscapeRoomIn)).thenReturn(entityEscapeRoomOut);

        try {
            TEscapeRoom testEscapeRoom = saEscapeRoom.crearEscapeRoom(tEscapeRoom);
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
    @DisplayName("Comprobación de que se lanza una exepción al intentar dar de alta un escape room existente en la BDD")
    public void CreateEscapeRoomAlreadyExists() {

        when(repositoryEscapeRoom.findEntityEscapeRoomByNombre("Test EscapeRoom")).thenReturn(entityEscapeRoomOut);

        try {
            saEscapeRoom.crearEscapeRoom(tEscapeRoom);
        } catch (Exception e) {
            Assertions.assertEquals(e.getClass(), InvalidNameException.class);
            Assertions.assertEquals(e.getMessage(), "Nombre no válido por existir en la base de datos");
        }
    }

    @Test
    @DisplayName("Comprobación de que se lanza una exepción al intentar dar de alta un escape room con un nombre inválido")
    public void CreateEscapeRoomWithInvalidName() {

        when(repositoryEscapeRoom.findEntityEscapeRoomByNombre("*[Nombre Inválido+?")).thenReturn(null);

        tEscapeRoom.setNombre("*[Nombre Inválido+?");
        try {
            saEscapeRoom.crearEscapeRoom(tEscapeRoom);
        } catch (Exception e) {
            Assertions.assertEquals(e.getClass(), InvalidNameCharactersException.class);
            Assertions.assertEquals(e.getMessage(), "Nombre no válido por contener los siguientes caracteres no permitidos: *[+?");
        }
    }

    @Test
    @DisplayName("Comprobación de que se lanza una exepción al intentar dar de alta un escape room con un nombre vacío")
    public void CreateEscapeRoomWithEmptyName() {

        when(repositoryEscapeRoom.findEntityEscapeRoomByNombre("")).thenReturn(null);

        tEscapeRoom.setNombre("");
        try {
            saEscapeRoom.crearEscapeRoom(tEscapeRoom);
        } catch (Exception e) {
            Assertions.assertEquals(e.getClass(), InvalidEmptyNameException.class);
            Assertions.assertEquals(e.getMessage(), "Nombre no válido por ser vacío");
        }
    }

    @Test
    @DisplayName("Comprobación de que se lanza una exepción al intentar dar de alta un escape room con un precio inválido")
    public void CreateEscapeRoomWithInvalidPrice() {

        when(repositoryEscapeRoom.findEntityEscapeRoomByNombre("Test EscapeRoom")).thenReturn(null);

        tEscapeRoom.setPrecio(-1);
        try {
            saEscapeRoom.crearEscapeRoom(tEscapeRoom);
        } catch (Exception e) {
            Assertions.assertEquals(e.getClass(), InvalidPriceException.class);
            Assertions.assertEquals(e.getMessage(), "Precio no válido, se considera válido un precio mayor o igual que 0");
        }
    }

    @Test
    @DisplayName("Comprobación de que se lanza una exepción al intentar dar de alta un escape room con una duración inválida")
    public void CreateEscapeRoomWithInvalidDuration() {

        when(repositoryEscapeRoom.findEntityEscapeRoomByNombre("Test EscapeRoom")).thenReturn(null);

        tEscapeRoom.setDuracion(-1);
        try {
            saEscapeRoom.crearEscapeRoom(tEscapeRoom);
        } catch (Exception e) {
            Assertions.assertEquals(e.getClass(), InvalidDurationException.class);
            Assertions.assertEquals(e.getMessage(), "Duración no válida, se considera válida una duración mayor que 0");
        }
    }

    @Test
    @DisplayName("Comprobación de que se lanza una exepción al intentar dar de alta un escape room con una capacidad de personas inválida")
    public void CreateEscapeRoomWithInvalidCapacity() {

        when(repositoryEscapeRoom.findEntityEscapeRoomByNombre("Test EscapeRoom")).thenReturn(null);

        tEscapeRoom.setCapacidadPersonas(0);
        try {
            saEscapeRoom.crearEscapeRoom(tEscapeRoom);
        } catch (Exception e) {
            Assertions.assertEquals(e.getClass(), InvalidCapacityException.class);
            Assertions.assertEquals(e.getMessage(), "Capacidad no válida. Se considera válida una capacidad mayor o igual que 1");
        }
    }
}