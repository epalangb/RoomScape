package roomscape.es.roomscapebackend.negocio.reserva;

import lombok.Data;

import java.util.Calendar;

@Data
public class TReserva implements Comparable<TReserva> {
    private int id;
    private int participantes;
    private boolean activo;
    private String nombreEscapeRoom;
    private Calendar fechaIni;
    private Calendar fechaFin;

    @Override
    public int compareTo(TReserva o) {
        if(getFechaIni() == null || o.getFechaIni() == null)
            return 0;
        return getFechaIni().compareTo(o.getFechaIni());
    }
}
