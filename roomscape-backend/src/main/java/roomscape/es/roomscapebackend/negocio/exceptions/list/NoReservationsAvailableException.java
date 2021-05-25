package roomscape.es.roomscapebackend.negocio.exceptions.list;

public class NoReservationsAvailableException extends EmptyListException {

    private static final String MESSAGE = "There are no reservations available at this time";

    public NoReservationsAvailableException() {
        super(MESSAGE);
    }

}
