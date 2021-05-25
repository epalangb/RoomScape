package roomscape.es.roomscapebackend.negocio.login;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import roomscape.es.roomscapebackend.negocio.client.TClient;
import roomscape.es.roomscapebackend.negocio.entity.EntityClient;
import roomscape.es.roomscapebackend.negocio.repository.RepositoryClient;

@SpringBootTest
public class SALoginTest {

    @Autowired
    SALogin saLogin;

    @Autowired
    RepositoryClient repositoryClient;

    @BeforeEach
    void init() {
        repositoryClient.deleteAll();
    }

    @Test
    @DisplayName("Check if the login works correctly")
    public void login() {
        TLogin tLogin = new TLogin();
        tLogin.setUser("Test1");
        tLogin.setPassword("cGFzc3dvcmQxMjNyb29tc2NhcGU=");

        EntityClient client = new EntityClient();
        client.setDni("15978534C");
        client.setUser("Test1");
        client.setPassword("cGFzc3dvcmQxMjNyb29tc2NhcGU=");//password123roomscape

        try {
            TClient clientCreated = repositoryClient.save(client).toTransfer();
            TClient tClientLogged = saLogin.login(tLogin);
            Assertions.assertEquals(tClientLogged.getUser(), clientCreated.getUser());
            Assertions.assertEquals(tClientLogged.getPassword(), clientCreated.getPassword());
        } catch (Exception e) {
            Assertions.assertNull(e);
        }
    }

}
