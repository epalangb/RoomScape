package roomscape.es.roomscapefrontend.app_controller.command.escape_room;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import roomscape.es.roomscapefrontend.utils.Configuration;
import roomscape.es.roomscapefrontend.models.TEscapeRoom;
import roomscape.es.roomscapefrontend.app_controller.command.Command;
import roomscape.es.roomscapefrontend.app_controller.Context;
import roomscape.es.roomscapefrontend.app_controller.controller.Controller;
import roomscape.es.roomscapefrontend.app_controller.Event;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.ArrayList;

public class ListarEscapeRoomCommand extends Command {

    private static final String CONNECTION_ERROR_MSG = "Ha ocurrido un error de comunicación con el servicio";
    private static final String PATH = "/escape-room/list";

    @Override
    public Context execute(Object data) {

        Configuration config = Controller.getInstance().getConfiguration();

        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(config.getBackendURL() + PATH))
                .GET()
                .timeout(Duration.ofSeconds(config.getTimeOut()))
                .build();

        Context context;

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                Type scapeRoomT = new TypeToken<ArrayList<TEscapeRoom>>() {
                }.getType();
                ArrayList<TEscapeRoom> scapeRooms = new Gson().fromJson(response.body(), scapeRoomT);
                context = new Context(scapeRooms, Event.ListarEscapeRoomOK);
            } else {
                context = new Context(response.body(), Event.ListarEscapeRoomError);
            }
        } catch (IOException | InterruptedException | JsonSyntaxException e) {
            context = new Context(CONNECTION_ERROR_MSG, Event.ListarEscapeRoomError);
        }

        return context;
    }
}
