package roomscape.es.roomscape;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import roomscape.es.roomscape.negocio.escape_room.SAEscapeRoom;
import roomscape.es.roomscape.negocio.escape_room.TEscapeRoom;

import javax.servlet.http.HttpServletResponse;
import java.util.Optional;


@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST})
public class WebController {

    private static final Logger log = LoggerFactory.getLogger(WebController.class);

    @Autowired
    SAEscapeRoom saEscapeRoom;

    @PostMapping(path = "/escape-room", consumes = "application/json")
    public TEscapeRoom addVideoGame(@RequestBody TEscapeRoom tEscapeRoom, HttpServletResponse response) {

        TEscapeRoom newEscapeRoom;

        Optional<TEscapeRoom> optional = null;
        try {
            optional = Optional.ofNullable(saEscapeRoom.crearEscapeRoom(tEscapeRoom));
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (optional.isPresent()) {
            response.setStatus(HttpServletResponse.SC_OK);
            newEscapeRoom = optional.get();
        } else {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            newEscapeRoom = new TEscapeRoom();
        }
        return newEscapeRoom;
    }
}
