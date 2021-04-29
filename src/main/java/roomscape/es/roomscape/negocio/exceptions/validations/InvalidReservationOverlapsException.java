package roomscape.es.roomscape.negocio.exceptions.validations;

public class InvalidReservationOverlapsException extends ValidationException{
    private static final String MESSAGE = "Ya existe una reserva para la Escape Room en el rango horario seleccionado";

    public InvalidReservationOverlapsException() {
        super(MESSAGE);
    }
}