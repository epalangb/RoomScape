package roomscape.es.roomscapebackend.negocio.exceptions.validations;

public class InvalidDurationException extends ValidationException {

    private static final String MESSAGE = "Duración no válida, se considera válida una duración mayor que 0";

    public InvalidDurationException() {
        super(MESSAGE);
    }
}
