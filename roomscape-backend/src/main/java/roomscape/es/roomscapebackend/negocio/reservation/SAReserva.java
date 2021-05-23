package roomscape.es.roomscapebackend.negocio.reservation;

public interface SAReserva {
    TReserva crearReserva(TReserva tReserva) throws Exception;
    List<TReserva> listByDateAndHour (Calendar reservationDate) throws Exception;
}
