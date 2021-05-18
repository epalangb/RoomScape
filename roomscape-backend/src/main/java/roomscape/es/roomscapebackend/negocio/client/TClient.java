package roomscape.es.roomscapebackend.negocio.client;

import lombok.Data;

@Data
public class TClient {
    private int id;
    private boolean active;
    private String dni;
    private String user;
    private String password;
}
