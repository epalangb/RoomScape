package roomscape.es.roomscape;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;
import roomscape.es.roomscape.negocio.escape_room.SAEscapeRoom;
import roomscape.es.roomscape.negocio.escape_room.TEscapeRoom;
import roomscape.es.roomscape.negocio.sa_factory.SAFactory;

@SpringBootApplication
public class RoomscapeApplication {

    @Autowired
    static SAEscapeRoom saEscapeRoom;

    public static void main(String[] args) {
        SpringApplication.run(RoomscapeApplication.class, args);
    }
}
