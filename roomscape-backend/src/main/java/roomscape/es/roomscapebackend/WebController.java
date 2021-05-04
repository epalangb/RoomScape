package roomscape.es.roomscapebackend;

import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import roomscape.es.roomscapebackend.negocio.escape_room.SAEscapeRoom;
import roomscape.es.roomscapebackend.negocio.escape_room.TEscapeRoom;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST})
public class WebController {

    private static final Logger log = LoggerFactory.getLogger(WebController.class);

    private static final String INTERNAL_SERVER_ERROR = "Ha ocurrido un error inesperado en el servidor";

    @Autowired
    SAEscapeRoom saEscapeRoom;

    @PostMapping(path = "/escape-room/create", consumes = "application/json")
    public String CreateEscapeRoom(@RequestBody TEscapeRoom tEscapeRoom, HttpServletResponse response) {

        log.debug("Iniciando la operación POST:CreateEscapeRoom para el escape room: {}", tEscapeRoom);

        TEscapeRoom newEscapeRoom;

        Optional<TEscapeRoom> optional = null;
        try {
            optional = Optional.ofNullable(saEscapeRoom.createEscapeRoom(tEscapeRoom));
        } catch (Exception e) {
            log.error("El servicio ha respondido con el siguiente error: {}", e.getMessage());
            response.setStatus(400);
            return e.getMessage();
        }
        if (optional.isPresent()) {
            response.setStatus(HttpServletResponse.SC_OK);
            newEscapeRoom = optional.get();
        } else {
            log.error("El servicio no ha respondido correctamente");
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            newEscapeRoom = new TEscapeRoom();
        }
        log.debug("Se ha creado correctamente el escape room: {}", newEscapeRoom);

        return new Gson().toJson(newEscapeRoom);
    }

    @PutMapping(path = "/escape-room/update", consumes = "application/json")
    public String UpdateEscapeRoom(@RequestBody TEscapeRoom tEscapeRoom, HttpServletResponse response) {

        log.debug("Iniciando la operación PUT:UpdateEscapeRoom para el escape room: {}", tEscapeRoom);

        TEscapeRoom tEscapeRoomUpdated;

        Optional<TEscapeRoom> optional;
        try {
            optional = Optional.ofNullable(saEscapeRoom.updateEscapeRoom(tEscapeRoom));
        } catch (Exception e) {
            log.error("El servicio ha respondido con el siguiente error: {}", e.getMessage());
            response.setStatus(400);
            return e.getMessage();
        }
        if (optional.isPresent()) {
            response.setStatus(HttpServletResponse.SC_OK);
            tEscapeRoomUpdated = optional.get();
        } else {
            log.error("El servicio no ha respondido correctamente");
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return INTERNAL_SERVER_ERROR;
        }

        log.debug("Se ha actualizado correctamente el escape room: {}", tEscapeRoomUpdated);

        return new Gson().toJson(tEscapeRoomUpdated);
    }

    @DeleteMapping(path = "/escape-room/delete/{id}", consumes = "application/json")
    public String DeleteEscapeRoom(@PathVariable(value = "id") int escapeRoomId, HttpServletResponse response) {

        log.debug("Iniciando la operación DELETE:DeleteEscapeRoom para el escape room con id: {}", escapeRoomId);

        int idRemoved;
        try {
            idRemoved = saEscapeRoom.deleteEscapeRoom(escapeRoomId);
        } catch (Exception e) {
            log.error("El servicio ha respondido con el siguiente error: {}", e.getMessage());
            response.setStatus(400);
            return e.getMessage();
        }
        if (idRemoved > 0) {
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            log.error("El servicio no ha respondido correctamente");
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return INTERNAL_SERVER_ERROR;
        }

        log.debug("Se ha eliminado correctamente el escape room con id: {}", idRemoved);

        return new Gson().toJson(idRemoved);
    }

    @GetMapping(path = "/escape-room/list")
    public String ListEscapeRoom(HttpServletResponse response) {

        log.debug("Iniciando la operación GET:ListEscapeRoom para listar todos los escape rooms");

        List<TEscapeRoom> escapeRoomList;

        Optional<List<TEscapeRoom>> optional;
        try {
            optional = Optional.ofNullable(saEscapeRoom.listEscapeRooms());
        } catch (Exception e) {
            log.error("El servicio ha respondido con el siguiente error: {}", e.getMessage());
            response.setStatus(400);
            return e.getMessage();
        }
        if (optional.isPresent()) {
            response.setStatus(HttpServletResponse.SC_OK);
            escapeRoomList = optional.get();
        } else {
            log.error("El servicio no ha respondido correctamente");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            escapeRoomList = new ArrayList<>();
        }

        log.debug("Se han recuperado correctamente los siguientes escape rooms: {}", escapeRoomList);

        return new Gson().toJson(escapeRoomList);
    }
}
