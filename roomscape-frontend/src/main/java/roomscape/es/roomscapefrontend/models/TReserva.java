package roomscape.es.roomscapefrontend.models;

import java.util.Calendar;
import lombok.Data;

@Data
public class TReserva {
    private int id;
    private int participantes;
    private boolean activo;
    private String nombreEscapeRoom;
    private Calendar fechaIni;
    private Calendar fechaFin;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(System.lineSeparator());
        sb.append(System.lineSeparator());
        sb.append("ID: " + id + System.lineSeparator());
        sb.append("Participantes: " + participantes + System.lineSeparator());
        sb.append("Nombre de Escape Room: " + nombreEscapeRoom + System.lineSeparator());
        sb.append("Fecha de inicio: " + fechaIni + System.lineSeparator());
        sb.append("Fecha de fin: " + fechaFin + System.lineSeparator());
        sb.append(System.lineSeparator());

        return sb.toString();
    }
}
