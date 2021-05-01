package es.roomscape.roomscapefrontend.presentacion.command.escape_room;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import es.roomscape.roomscapefrontend.presentacion.Event;
import es.roomscape.roomscapefrontend.presentacion.command.Command;
import es.roomscape.roomscapefrontend.presentacion.controller.Context;
import roomscape.es.roomscapebackend.negocio.escape_room.TEscapeRoom;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public class AltaEscapeRoomBasicoCommand extends Command {
    @Override
    public Context execute(Object data) {

        String body = new Gson().toJson(data);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/escape-room"))
                .headers("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .timeout(Duration.ofSeconds(50))
                .build();

        Context context;

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                TEscapeRoom tEscapeRoom = new Gson().fromJson(response.body(), TEscapeRoom.class);
                context = new Context(tEscapeRoom, Event.AltaEscapeRoomBasicoOK);
            } else {
                context = new Context(response.body(), Event.AltaEscapeRoomBasicoError);
            }
        } catch (IOException | InterruptedException | JsonSyntaxException e) {
            String error = "Ha ocurrido un error de comunicación con el servicio.";
            context = new Context(error, Event.AltaEscapeRoomBasicoError);
        }

        return context;
    }
}
