package roomscape.es.roomscapebackend.negocio.reservation;

import lombok.Data;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Data
public class TReserva {
    private int id;
    private int participantes;
    private double precio;
    private int duracion;
    private String cliente;
    private boolean activo;
    private String nombreEscapeRoom;
    private String fechaIni;
    private String fechaFin;

    public Calendar getFechaIniInDateFormat(){
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        try {
            cal.setTime(sdf.parse(this.fechaIni));
        } catch (ParseException e) {
            return null;
        }
        return cal;
    }

    public Calendar getFechaFinInDateFormat(){
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        try {
            cal.setTime(sdf.parse(this.fechaFin));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return cal;
    }
}
