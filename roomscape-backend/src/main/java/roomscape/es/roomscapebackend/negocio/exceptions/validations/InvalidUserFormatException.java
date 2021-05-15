package roomscape.es.roomscapebackend.negocio.exceptions.validations;

public class InvalidUserFormatException extends ValidationException {

    private static final String MESSAGE = "El nombre de usuario contiene caracteres no válidos";

    public InvalidUserFormatException() {
        super(MESSAGE);
    }
}
