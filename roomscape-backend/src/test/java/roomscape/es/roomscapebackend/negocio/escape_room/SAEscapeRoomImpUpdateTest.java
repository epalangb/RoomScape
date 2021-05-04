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
import roomscape.es.roomscapebackend.negocio.exceptions.validations.*;
import roomscape.es.roomscapebackend.negocio.repository.RepositoryEscapeRoom;

import java.util.Optional;

import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class SAEscapeRoomImpUpdateTest {

    @Mock
    RepositoryEscapeRoom repositoryEscapeRoom;

    @InjectMocks
    SAEscapeRoom saEscapeRoom = new SAEscapeRoomImp();


    private TEscapeRoom tEscapeRoom;
    private EntityEscapeRoom entityEscapeRoomOriginal;
    private EntityEscapeRoom entityEscapeRoomNew;
    private EntityEscapeRoom entityEscapeRoomOther;

    @BeforeEach
    void setMockOutput() {

        tEscapeRoom = new TEscapeRoom();
        tEscapeRoom.setActivo(true);
        tEscapeRoom.setCapacidadPersonas(10);
        tEscapeRoom.setDuracion(40);
        tEscapeRoom.setId(1);
        tEscapeRoom.setNombre("NEW_NAME");
        tEscapeRoom.setPrecio(40);

        entityEscapeRoomNew = new EntityEscapeRoom(tEscapeRoom);

        entityEscapeRoomOriginal = new EntityEscapeRoom();
        entityEscapeRoomOriginal.setActivo(true);
        entityEscapeRoomOriginal.setCapacidadPersonas(5);
        entityEscapeRoomOriginal.setDuracion(20);
        entityEscapeRoomOriginal.setId(1);
        entityEscapeRoomOriginal.setNombre("ORIGINAL_NAME");
        entityEscapeRoomOriginal.setPrecio(20);

        entityEscapeRoomOther = new EntityEscapeRoom();
        entityEscapeRoomOther.setActivo(true);
        entityEscapeRoomOther.setCapacidadPersonas(5);
        entityEscapeRoomOther.setDuracion(20);
        entityEscapeRoomOther.setId(2);
        entityEscapeRoomOther.setNombre("NEW_NAME");
        entityEscapeRoomOther.setPrecio(20);
    }

    @Test
    @DisplayName("Comprobación de que un escape room se actualiza correctamente")
    public void UpdateEscapeRoom() {

        when(repositoryEscapeRoom.findById(1)).thenReturn(Optional.ofNullable(entityEscapeRoomOriginal));
        when(repositoryEscapeRoom.findEntityEscapeRoomByNombre("NEW_NAME")).thenReturn(Optional.ofNullable(null));
        when(repositoryEscapeRoom.save(entityEscapeRoomNew)).thenReturn(entityEscapeRoomNew);

        try {
            TEscapeRoom testEscapeRoom = saEscapeRoom.updateEscapeRoom(tEscapeRoom);
            Assertions.assertEquals(testEscapeRoom.getId(), 1);
            Assertions.assertEquals(testEscapeRoom.getNombre(), "NEW_NAME");
            Assertions.assertTrue(testEscapeRoom.isActivo());
            Assertions.assertEquals(testEscapeRoom.getDuracion(), 40);
            Assertions.assertEquals(testEscapeRoom.getPrecio(), 40);
            Assertions.assertEquals(testEscapeRoom.getCapacidadPersonas(), 10);
        } catch (Exception e) {
            Assertions.assertNull(e);
        }
    }

    @Test
    @DisplayName("Comprobación de que se lanza una exepción al intentar actualizar un escape room con un nombre existente en la BDD")
    public void UpdateEscapeRoomAlreadyExists() {

        when(repositoryEscapeRoom.findById(1)).thenReturn(Optional.ofNullable(entityEscapeRoomOriginal));
        when(repositoryEscapeRoom.findEntityEscapeRoomByNombre("NEW_NAME")).thenReturn(Optional.ofNullable(entityEscapeRoomOther));
        try {
            saEscapeRoom.updateEscapeRoom(tEscapeRoom);
        } catch (Exception e) {
            Assertions.assertEquals(e.getClass(), InvalidNameException.class);
            Assertions.assertEquals(e.getMessage(), "Nombre no válido por existir en la base de datos");
        }
    }

    @Test
    @DisplayName("Comprobación de que se lanza una exepción al intentar actualizar un escape room con un nombre inválido")
    public void UpdateEscapeRoomWithInvalidName() {

        when(repositoryEscapeRoom.findById(1)).thenReturn(Optional.ofNullable(entityEscapeRoomOriginal));
        when(repositoryEscapeRoom.findEntityEscapeRoomByNombre("*[NEW_NAME+?")).thenReturn(Optional.ofNullable(null));

        tEscapeRoom.setNombre("*[NEW_NAME+?");
        try {
            saEscapeRoom.updateEscapeRoom(tEscapeRoom);
        } catch (Exception e) {
            Assertions.assertEquals(e.getClass(), InvalidNameCharactersException.class);
            Assertions.assertEquals(e.getMessage(), "Nombre no válido por contener los siguientes caracteres no permitidos: *[+?");
        }
    }

    @Test
    @DisplayName("Comprobación de que se lanza una exepción al intentar actualizar un escape room con un nombre vacío")
    public void UpdateEscapeRoomWithEmptyName() {

        when(repositoryEscapeRoom.findById(1)).thenReturn(Optional.ofNullable(entityEscapeRoomOriginal));
        when(repositoryEscapeRoom.findEntityEscapeRoomByNombre("")).thenReturn(Optional.ofNullable(null));

        tEscapeRoom.setNombre("");
        try {
            saEscapeRoom.updateEscapeRoom(tEscapeRoom);
        } catch (Exception e) {
            Assertions.assertEquals(e.getClass(), InvalidEmptyNameException.class);
            Assertions.assertEquals(e.getMessage(), "Nombre no válido por ser vacío");
        }
    }

    @Test
    @DisplayName("Comprobación de que se lanza una exepción al intentar actualizar un escape room inactivo")
    public void UpdateEscapeRoomInactive() {

        entityEscapeRoomOriginal.setActivo(false);
        when(repositoryEscapeRoom.findById(1)).thenReturn(Optional.ofNullable(entityEscapeRoomOriginal));

        try {
            saEscapeRoom.updateEscapeRoom(tEscapeRoom);
        } catch (Exception e) {
            Assertions.assertEquals(e.getClass(), NonExistentEscapeRoom.class);
            Assertions.assertEquals(e.getMessage(), "No se puede realizar la operación porque el escape room con id: 1 no existe");
        }
    }

    @Test
    @DisplayName("Comprobación de que se lanza una exepción al intentar actualizar un escape room con un precio inválido")
    public void UpdateEscapeRoomWithInvalidPrice() {

        when(repositoryEscapeRoom.findById(1)).thenReturn(Optional.ofNullable(entityEscapeRoomOriginal));
        when(repositoryEscapeRoom.findEntityEscapeRoomByNombre("NEW_NAME")).thenReturn(Optional.ofNullable(null));

        tEscapeRoom.setPrecio(-1);
        try {
            saEscapeRoom.updateEscapeRoom(tEscapeRoom);
        } catch (Exception e) {
            Assertions.assertEquals(e.getClass(), InvalidPriceException.class);
            Assertions.assertEquals(e.getMessage(), "Precio no válido, se considera válido un precio mayor o igual que 0");
        }
    }

    @Test
    @DisplayName("Comprobación de que se lanza una exepción al intentar actualizar un escape room con una duración inválida")
    public void UpdateEscapeRoomWithInvalidDuration() {

        when(repositoryEscapeRoom.findById(1)).thenReturn(Optional.ofNullable(entityEscapeRoomOriginal));
        when(repositoryEscapeRoom.findEntityEscapeRoomByNombre("NEW_NAME")).thenReturn(Optional.ofNullable(null));

        tEscapeRoom.setDuracion(-1);
        try {
            saEscapeRoom.updateEscapeRoom(tEscapeRoom);
        } catch (Exception e) {
            Assertions.assertEquals(e.getClass(), InvalidDurationException.class);
            Assertions.assertEquals(e.getMessage(), "Duración no válida, se considera válida una duración mayor que 0");
        }
    }

    @Test
    @DisplayName("Comprobación de que se lanza una exepción al intentar actualizar un escape room con una capacidad de personas inválida")
    public void UpdateEscapeRoomWithInvalidCapacity() {

        when(repositoryEscapeRoom.findById(1)).thenReturn(Optional.ofNullable(entityEscapeRoomOriginal));
        when(repositoryEscapeRoom.findEntityEscapeRoomByNombre("NEW_NAME")).thenReturn(Optional.ofNullable(null));

        tEscapeRoom.setCapacidadPersonas(0);
        try {
            saEscapeRoom.updateEscapeRoom(tEscapeRoom);
        } catch (Exception e) {
            Assertions.assertEquals(e.getClass(), InvalidCapacityException.class);
            Assertions.assertEquals(e.getMessage(), "Capacidad no válida. Se considera válida una capacidad mayor o igual que 1");
        }
    }
}