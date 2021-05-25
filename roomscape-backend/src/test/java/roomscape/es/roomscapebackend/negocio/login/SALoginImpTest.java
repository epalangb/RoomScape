package roomscape.es.roomscapebackend.negocio.login;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import roomscape.es.roomscapebackend.negocio.client.SAClient;
import roomscape.es.roomscapebackend.negocio.client.SAClientImp;
import roomscape.es.roomscapebackend.negocio.client.TClient;
import roomscape.es.roomscapebackend.negocio.entity.EntityClient;
import roomscape.es.roomscapebackend.negocio.exceptions.validations.InvalidNameException;
import roomscape.es.roomscapebackend.negocio.exceptions.validations.InvalidPasswordException;
import roomscape.es.roomscapebackend.negocio.exceptions.validations.InvalidUserNotExistsException;
import roomscape.es.roomscapebackend.negocio.repository.RepositoryClient;

import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SALoginImpTest {

    @Mock
    RepositoryClient repositoryClient;

    @InjectMocks
    SALogin saLogin = new SALoginImp();

    private TLogin tLogin;
    private EntityClient entityClientOut;

    @BeforeEach
    void setMockOutput() {

        tLogin = new TLogin();
        tLogin.setUser("Test1");
        tLogin.setPassword("cGFzc3dvcmQxMjNyb29tc2NhcGU="); //password123roomscape

        entityClientOut = new EntityClient();
        entityClientOut.setDni("15978534C");
        entityClientOut.setUser("Test1");
        entityClientOut.setActive(true);
        entityClientOut.setId(1);
        entityClientOut.setPassword("cGFzc3dvcmQxMjNyb29tc2NhcGU=");
    }

    @Test
    @DisplayName("Check if the login works correctly")
    public void loginClientOK() {

        when(repositoryClient.findEntityClientByUser("Test1")).thenReturn(Optional.ofNullable(entityClientOut));

        try {
            TClient tClientLogged = saLogin.login(tLogin);
            Assertions.assertEquals(tClientLogged.getUser(), tLogin.getUser());
            Assertions.assertEquals(tClientLogged.getPassword(), tLogin.getPassword());
        } catch (Exception e) {
            Assertions.assertNull(e);
        }
    }

    @Test
    @DisplayName("Check that the login failed when the user does not exist")
    public void loginClientDoesNotExist() {

        when(repositoryClient.findEntityClientByUser("Test1")).thenReturn(Optional.ofNullable(null));

        try {
            saLogin.login(tLogin);
        } catch (Exception e) {
            Assertions.assertEquals(e.getClass(), InvalidUserNotExistsException.class);
            Assertions.assertEquals(e.getMessage(), "El usuario no existe en la base de datos");
        }
    }

    @Test
    @DisplayName("Check that the user exists but the password is wrong")
    public void loginClientExistsButIncorrectPassword() {

        when(repositoryClient.findEntityClientByUser("Test1")).thenReturn(Optional.ofNullable(entityClientOut));

        try {
            tLogin.setPassword("2344r4er32jr4930");
            saLogin.login(tLogin);
        } catch (Exception e) {
            Assertions.assertEquals(e.getClass(), InvalidPasswordException.class);
            Assertions.assertEquals(e.getMessage(), "La contraseña es incorrecta");
        }
    }

}
