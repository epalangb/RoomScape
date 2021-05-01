package roomscape.es.roomscape;

import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import roomscape.es.roomscape.negocio.escape_room.SAEscapeRoom;
import roomscape.es.roomscape.negocio.escape_room.TEscapeRoom;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST})
public class WebController {

    private static final Logger log = LoggerFactory.getLogger(WebController.class);

    @Autowired
    SAEscapeRoom saEscapeRoom;

    @PostMapping(path = "/escape-room", consumes = "application/json")
    public String createEscapeRoom(@RequestBody TEscapeRoom tEscapeRoom, HttpServletResponse response) {

        TEscapeRoom newEscapeRoom;

        Optional<TEscapeRoom> optional = null;
        try {
            optional = Optional.ofNullable(saEscapeRoom.crearEscapeRoom(tEscapeRoom));
        } catch (Exception e) {
            response.setStatus(400);
            return e.getMessage();
        }
        if (optional.isPresent()) {
            response.setStatus(HttpServletResponse.SC_OK);
            newEscapeRoom = optional.get();
        } else {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            newEscapeRoom = new TEscapeRoom();
        }
        return new Gson().toJson(newEscapeRoom);
    }

    @GetMapping(path = "/escape-room/list")
    public String listEscapeRoom(HttpServletResponse response) {

        List<TEscapeRoom> escapeRoomList;

        Optional<List<TEscapeRoom>> optional = null;
        try {
            optional = Optional.ofNullable(saEscapeRoom.listarEscapeRooms());
        } catch (Exception e) {
            response.setStatus(400);
            return e.getMessage();
        }
        if (optional.isPresent()) {
            response.setStatus(HttpServletResponse.SC_OK);
            escapeRoomList = optional.get();
        } else {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            escapeRoomList = new ArrayList<TEscapeRoom>();
        }
        return new Gson().toJson(escapeRoomList);
    }
}
