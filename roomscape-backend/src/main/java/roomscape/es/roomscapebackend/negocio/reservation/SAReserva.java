package roomscape.es.roomscapebackend.negocio.reservation;

import java.util.List;

public interface SAReserva {
    TReserva crearReserva(TReserva tReserva) throws Exception;

    List<TReserva> getReservationsByEscapeRoomId(int id) throws Exception;
}
