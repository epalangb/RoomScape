package roomscape.es.roomscapebackend.negocio.reservation;

import java.util.Calendar;
import java.util.List;

public interface SAReserva {
    TReserva crearReserva(TReserva tReserva) throws Exception;
    List<TReserva> listByDateAndHour (Calendar reservationDate) throws Exception;
}
