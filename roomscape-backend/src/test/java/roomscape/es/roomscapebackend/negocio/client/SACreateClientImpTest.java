package roomscape.es.roomscapebackend.negocio.client;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import roomscape.es.roomscapebackend.negocio.entity.EntityClient;
import roomscape.es.roomscapebackend.negocio.exceptions.validations.*;
import roomscape.es.roomscapebackend.negocio.repository.RepositoryClient;

import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SACreateClientImpTest {

    @Mock
    RepositoryClient repositoryClient;

    @InjectMocks
    SAClient saClient = new SAClientImp();


    private TClient tClient;
    private EntityClient entityClientIn;
    private EntityClient entityClientOut;

    @BeforeEach
    void setMockOutput() {
        tClient = new TClient();
        tClient.setDni("15978534C");
        tClient.setUser("Test1");
        tClient.setPassword("password123roomscape");

        entityClientIn = new EntityClient();
        entityClientIn.setDni("15978534C");
        entityClientIn.setUser("Test1");
        entityClientIn.setActive(true);
        entityClientIn.setPassword("password123roomscape");

        entityClientOut = new EntityClient();
        entityClientOut.setDni("15978534C");
        entityClientOut.setUser("Test1");
        entityClientOut.setActive(true);
        entityClientOut.setId(1);
        entityClientOut.setPassword("password123roomscape");
    }

    @Test
    @DisplayName("Check if the client has been created correctly")
    public void createClient() {

        when(repositoryClient.findEntityClientByDni("15978534C")).thenReturn(Optional.ofNullable(null));
        when(repositoryClient.findEntityClientByUser("Test1")).thenReturn(Optional.ofNullable(null));
        when(repositoryClient.save(entityClientIn)).thenReturn(entityClientOut);

        try {
            TClient tClientSaved = saClient.createClient(tClient);
            Assertions.assertEquals(tClientSaved.getId(), 1);
            Assertions.assertEquals(tClientSaved.getDni(), "15978534C");
            Assertions.assertTrue(tClientSaved.isActive());
            Assertions.assertEquals(tClientSaved.getUser(), "Test1");
            Assertions.assertEquals(tClientSaved.getPassword(), "password123roomscape");
        } catch (Exception e) {
            Assertions.assertNull(e);
        }
    }

    @Test
    @DisplayName("Check that we can not have two clients with the same DNI")
    public void createClientAlreadyDNIExists() {

        when(repositoryClient.findEntityClientByDni("15978534C")).thenReturn(Optional.ofNullable(entityClientOut));
        when(repositoryClient.findEntityClientByUser("Test1")).thenReturn(Optional.ofNullable(null));

        try {
            saClient.createClient(tClient);
        } catch (Exception e) {
            Assertions.assertEquals(e.getClass(), InvalidDniExistsException.class);
            Assertions.assertEquals(e.getMessage(), "Dni no válido por existir en la base de datos");
        }
    }

    @Test
    @DisplayName("Check that we can not have two clients with the same user")
    public void createClientAlreadyUserExists() {

        when(repositoryClient.findEntityClientByDni("15978534C")).thenReturn(Optional.ofNullable(null));
        when(repositoryClient.findEntityClientByUser("Test1")).thenReturn(Optional.ofNullable(entityClientOut));

        try {
            saClient.createClient(tClient);
        } catch (Exception e) {
            Assertions.assertEquals(e.getClass(), InvalidNameException.class);
            Assertions.assertEquals(e.getMessage(), "Nombre no válido por existir en la base de datos");
        }
    }

    @Test
    @DisplayName("Check that we can not have a invalid DNI length in the BBDD")
    public void createClientWithInvalidLengthDNI() {

        when(repositoryClient.findEntityClientByDni("1597534C")).thenReturn(Optional.ofNullable(null));
        when(repositoryClient.findEntityClientByUser("Test1")).thenReturn(Optional.ofNullable(null));

        try {
            tClient.setDni("1597534C");
            saClient.createClient(tClient);
        } catch (Exception e) {
            Assertions.assertEquals(e.getClass(), InvalidDniException.class);
            Assertions.assertEquals(e.getMessage(), "Dni con formato no válido");
        }
    }

    @Test
    @DisplayName("Check that we can not have a invalid DNI, without the last character, in the BBDD")
    public void createClientWithoutCharacterDNI() {

        when(repositoryClient.findEntityClientByDni("15978534")).thenReturn(Optional.ofNullable(null));
        when(repositoryClient.findEntityClientByUser("Test1")).thenReturn(Optional.ofNullable(null));

        try {
            tClient.setDni("15978534");
            saClient.createClient(tClient);
        } catch (Exception e) {
            Assertions.assertEquals(e.getClass(), InvalidDniException.class);
            Assertions.assertEquals(e.getMessage(), "Dni con formato no válido");
        }
    }

    @Test
    @DisplayName("Check that we can not have a invalid DNI in the BBDD")
    public void createClientWithInvalidDNI() {

        when(repositoryClient.findEntityClientByDni("25978534C")).thenReturn(Optional.ofNullable(null));
        when(repositoryClient.findEntityClientByUser("Test1")).thenReturn(Optional.ofNullable(null));

        try {
            tClient.setDni("25978534C");
            saClient.createClient(tClient);
        } catch (Exception e) {
            Assertions.assertEquals(e.getClass(), InvalidDniException.class);
            Assertions.assertEquals(e.getMessage(), "Dni con formato no válido");
        }
    }

    @Test
    @DisplayName("Check that we can not have a user with invalid characters")
    public void createClientWithInvalidCharacters() {

        when(repositoryClient.findEntityClientByDni("15978534C")).thenReturn(Optional.ofNullable(null));
        when(repositoryClient.findEntityClientByUser("Test1*")).thenReturn(Optional.ofNullable(null));

        try {
            tClient.setUser("Test1*");
            saClient.createClient(tClient);
        } catch (Exception e) {
            Assertions.assertEquals(e.getClass(), InvalidUserFormatException.class);
            Assertions.assertEquals(e.getMessage(), "El nombre de usuario contiene caracteres no válidos");
        }
    }

    @Test
    @DisplayName("Check that we can not have a user with invalid characters")
    public void createClientWithEmptyUser() {

        when(repositoryClient.findEntityClientByDni("15978534C")).thenReturn(Optional.ofNullable(null));
        when(repositoryClient.findEntityClientByUser("")).thenReturn(Optional.ofNullable(null));

        try {
            tClient.setUser("");
            saClient.createClient(tClient);
        } catch (Exception e) {
            Assertions.assertEquals(e.getClass(), InvalidEmptyNameException.class);
            Assertions.assertEquals(e.getMessage(), "Nombre no válido por ser vacío");
        }
    }

    @Test
    @DisplayName("Check that we can not have a user with an empty DNI")
    public void createClientWithEmptyDNI() {

        when(repositoryClient.findEntityClientByDni("")).thenReturn(Optional.ofNullable(null));
        when(repositoryClient.findEntityClientByUser("Test1")).thenReturn(Optional.ofNullable(null));

        try {
            tClient.setDni("");
            saClient.createClient(tClient);
        } catch (Exception e) {
            Assertions.assertEquals(e.getClass(), InvalidDniException.class);
            Assertions.assertEquals(e.getMessage(), "Dni con formato no válido");
        }
    }

    @Test
    @DisplayName("Check that we can not have a password with invalid characters")
    public void createPasswordWithInvalidCharacters() {

        when(repositoryClient.findEntityClientByDni("15978534C")).thenReturn(Optional.ofNullable(null));
        when(repositoryClient.findEntityClientByUser("Test1")).thenReturn(Optional.ofNullable(null));

        try {
            tClient.setPassword("password*roomscape");
            saClient.createClient(tClient);
        } catch (Exception e) {
            Assertions.assertEquals(e.getClass(), InvalidPasswordFormatException.class);
            Assertions.assertEquals(e.getMessage(), "Formato de contraseña no valido");
        }
    }

    @Test
    @DisplayName("Check that we can not have an empty password")
    public void createAnEmptyPassword() {

        when(repositoryClient.findEntityClientByDni("15978534C")).thenReturn(Optional.ofNullable(null));
        when(repositoryClient.findEntityClientByUser("Test1")).thenReturn(Optional.ofNullable(null));

        try {
            tClient.setPassword("");
            saClient.createClient(tClient);
        } catch (Exception e) {
            Assertions.assertEquals(e.getClass(), InvalidPasswordFormatException.class);
            Assertions.assertEquals(e.getMessage(), "Formato de contraseña no valido");
        }
    }

}
