package roomscape.es.roomscapebackend.negocio.entity;

import lombok.Data;
import roomscape.es.roomscapebackend.negocio.client.TClient;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class EntityClient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private boolean active;
    private String dni;
    private String user;
    private String password;

    public EntityClient(){
    }

    public EntityClient(TClient tClient){
        this.id = tClient.getId();
        this.active = tClient.isActive();
        this.dni = tClient.getDni();
        this.user = tClient.getUser();
        this.password = tClient.getPassword();
    }

    public TClient toTransfer(){
        TClient tClient = new TClient();
        tClient.setId(this.id);
        tClient.setActive(this.active);
        tClient.setPassword(this.password);
        tClient.setUser(this.user);
        return tClient;
    }
}
