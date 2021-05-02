package roomscape.es.roomscapebackend.negocio.exceptions.validations;

public class InvalidReservationException extends ValidationException {
    private static final String MESSAGE = "Ya existe una reserva para la Escape Room y hora seleccionadas";

    public InvalidReservationException() {
        super(MESSAGE);
    }
}