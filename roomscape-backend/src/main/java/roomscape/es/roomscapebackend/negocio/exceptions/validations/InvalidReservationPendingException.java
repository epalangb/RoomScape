package roomscape.es.roomscapebackend.negocio.exceptions.validations;

public class InvalidReservationPendingException extends ValidationException {

    private static final String MESSAGE = "No se puede dar de baja el escape room porque tiene reservas pendientes";

    public InvalidReservationPendingException() {
        super(MESSAGE);
    }
}