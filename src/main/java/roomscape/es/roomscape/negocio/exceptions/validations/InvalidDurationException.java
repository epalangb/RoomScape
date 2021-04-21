package roomscape.es.roomscape.negocio.exceptions.validations;

public class InvalidDurationException extends ValidationException {

    private static final String MESSAGE = "La duración no es válida por no ser mayor que 0";

    public InvalidDurationException() {
        super(MESSAGE);
    }
}
