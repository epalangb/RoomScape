package roomscape.es.roomscapefrontend.app_controller.command.escape_room;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import roomscape.es.roomscapefrontend.app_controller.Context;
import roomscape.es.roomscapefrontend.app_controller.Event;
import roomscape.es.roomscapefrontend.app_controller.command.Command;
import roomscape.es.roomscapefrontend.app_controller.controller.Controller;
import roomscape.es.roomscapefrontend.models.TEscapeRoom;
import roomscape.es.roomscapefrontend.utils.Configuration;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public class BajaEscapeRoomCommand extends Command {

    private static final String CONNECTION_ERROR_MSG = "Ha ocurrido un error de comunicación con el servicio";
    private static final String PATH = "/escape-room/delete/";

    @Override
    public Context execute(Object data) {

        Configuration config = Controller.getInstance().getConfiguration();

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(config.getBackendURL() + PATH + data))
                .GET()
                .timeout(Duration.ofSeconds(config.getTimeOut()))
                .build();

        Context context;

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                context = new Context(data, Event.BajaEscapeRoomOK);
            } else {
                context = new Context(response.body(), Event.BajaEScapeRoomError);
            }
        } catch (IOException | InterruptedException | JsonSyntaxException e) {
            context = new Context(CONNECTION_ERROR_MSG, Event.BajaEScapeRoomError);
        }

        return context;
    }
}