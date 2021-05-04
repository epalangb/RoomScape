package roomscape.es.roomscapebackend.negocio.exceptions.validations;

public class InvalidNumberOfParticipantsException extends ValidationException {
    private static final String MESSAGE = "El numero de participantes tiene que ser mayor que cero";

    public InvalidNumberOfParticipantsException() {
        super(MESSAGE);
    }
}