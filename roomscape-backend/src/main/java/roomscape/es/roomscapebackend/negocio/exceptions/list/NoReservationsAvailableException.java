package roomscape.es.roomscapebackend.negocio.exceptions.list;

public class NoReservationsAvailableException extends EmptyListException {

    private static final String MESSAGE = "There are not reservations available at the moment";

    public NoReservationsAvailableException() {
        super(MESSAGE);
    }

}
