package roomscape.es.roomscapebackend.negocio.client;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class SACreateClientTest {

    @Autowired
    SAClient saClient;

    @Test
    @DisplayName("Check if the client has been created correctly")
    public void createClient() {

        TClient tClient = new TClient();
        tClient.setDni("15978534C");
        tClient.setUser("Test1");
        tClient.setPassword("cGFzc3dvcmQxMjNyb29tc2NhcGU="); //password123roomscape

        try {
            TClient tClientSaved = saClient.createClient(tClient);
            Assertions.assertEquals(tClientSaved.getDni(), tClient.getDni());
            Assertions.assertEquals(tClientSaved.getUser(), tClient.getUser());
            Assertions.assertEquals(tClientSaved.getId(), 1);
            Assertions.assertEquals(tClientSaved.getPassword(), tClient.getPassword());
            Assertions.assertTrue(tClientSaved.isActive());
        } catch (Exception e) {
            Assertions.assertNull(e);
        }
    }

}
