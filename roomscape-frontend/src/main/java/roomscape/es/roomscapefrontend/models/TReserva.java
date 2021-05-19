package roomscape.es.roomscapefrontend.models;

import lombok.Data;

import java.util.Calendar;

@Data
public class TReserva {
    private int id;
    private int participantes;
    private double precio;
    private int duracion;
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
        sb.append("Nombre: " + nombreEscapeRoom + System.lineSeparator());
        sb.append("Participantes: " + participantes + System.lineSeparator());
        sb.append("Duración: " + duracion + System.lineSeparator());
        sb.append("Precio: " + precio + System.lineSeparator());
        sb.append("FechaIni:" + fechaIni + System.lineSeparator());
        sb.append("FechaFin:" + fechaFin + System.lineSeparator());
        sb.append(System.lineSeparator());

        return sb.toString();
    }
}
