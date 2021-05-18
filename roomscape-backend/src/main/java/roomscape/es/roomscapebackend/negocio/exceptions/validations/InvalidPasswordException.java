package roomscape.es.roomscapebackend.negocio.exceptions.validations;

public class InvalidPasswordException extends ValidationException {

    private static final String MESSAGE = "La contraseña es incorrecta";

    public InvalidPasswordException() {
        super(MESSAGE);
    }
}
