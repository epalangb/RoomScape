package roomscape.es.roomscapebackend.negocio.exceptions.validations;

public class InvalidParticipantException extends ValidationException {
    private static final String MESSAGE = "El numero de participantes supera la capacidad de la Escape Room";

    public InvalidParticipantException() {
        super(MESSAGE);
    }
}