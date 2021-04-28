package roomscape.es.roomscape.presentacion.command.escape_room;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import roomscape.es.roomscape.negocio.escape_room.TEscapeRoom;
import roomscape.es.roomscape.presentacion.Configuration;
import roomscape.es.roomscape.presentacion.Eventos;
import roomscape.es.roomscape.presentacion.command.Command;
import roomscape.es.roomscape.presentacion.controller.Context;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public class ListarEscapeRoomCommand extends Command {

    private static final String CONNECTION_ERROR_MSG = "Ha ocurrido un error de comunicación con el servicio";
    private static final String PATH = "/escape-room";
    private static final String CONTENT_TYPE_HEADER = "Content-Type";
    private static final String CONTENT_TYPE_HEADER_VALUE = "application/json";
    private static final int DEFAULT_TIMEOUT = 10;

    @Override
    public Context execute(Object datos) {

        String body = new Gson().toJson(datos);

        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(Configuration.ROOMSCAPE_BACKEND_URL + PATH))
                .headers(CONTENT_TYPE_HEADER, CONTENT_TYPE_HEADER_VALUE)
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .timeout(Duration.ofSeconds(DEFAULT_TIMEOUT))
                .build();

        Context context;

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == HttpServletResponse.SC_OK) {
                TEscapeRoom tEscapeRoom = new Gson().fromJson(response.body(), TEscapeRoom.class);
                context = new Context(tEscapeRoom, Eventos.ListarEscapeRoomOK);
            } else {
                context = new Context(response.body(), Eventos.ListarEscapeRoomError);
            }
        } catch (IOException | InterruptedException | JsonSyntaxException e) {
            context = new Context(CONNECTION_ERROR_MSG, Eventos.ListarEscapeRoomError);
        }

        return context;
    }
}
