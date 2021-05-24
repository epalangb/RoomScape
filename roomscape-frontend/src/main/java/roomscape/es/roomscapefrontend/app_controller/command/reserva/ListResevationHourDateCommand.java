package roomscape.es.roomscapefrontend.app_controller.command.reserva;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import roomscape.es.roomscapefrontend.app_controller.Context;
import roomscape.es.roomscapefrontend.app_controller.Event;
import roomscape.es.roomscapefrontend.app_controller.command.Command;
import roomscape.es.roomscapefrontend.app_controller.controller.Controller;
import roomscape.es.roomscapefrontend.models.TEscapeRoom;
import roomscape.es.roomscapefrontend.models.TReserva;
import roomscape.es.roomscapefrontend.utils.Configuration;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.ArrayList;

public class ListResevationHourDateCommand extends Command {

    private static final String CONNECTION_ERROR_MSG = "Ha ocurrido un error de comunicación con el servicio";
    private static final String PATH = "/reservation/list";
    private static final String CONTENT_TYPE_HEADER = "Content-Type";
    private static final String CONTENT_TYPE_HEADER_VALUE = "application/json";

    @Override
    public Context execute(Object data) {

        Configuration config = Controller.getInstance().getConfiguration();
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(config.getBackendURL() + PATH))
                .headers(CONTENT_TYPE_HEADER, CONTENT_TYPE_HEADER_VALUE)
                .POST(HttpRequest.BodyPublishers.ofString((String) data))
                .timeout(Duration.ofSeconds(config.getTimeOut()))
                .build();

        Context context;

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                Type reservaT = new TypeToken<ArrayList<TReserva>>() {
                }.getType();
                ArrayList<TEscapeRoom> scapeRooms = new Gson().fromJson(response.body(), reservaT);
                context = new Context(scapeRooms, Event.ListReservas);
            } else {
                context = new Context(response.body(), Event.ListReservasError);
            }
        } catch (IOException | InterruptedException | JsonSyntaxException e) {
            context = new Context(CONNECTION_ERROR_MSG, Event.ListReservasError);
        }

        return context;
    }
}
