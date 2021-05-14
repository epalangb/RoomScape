package roomscape.es.roomscapebackend.negocio.exceptions.validations;

public class InvalidPasswordFormatException extends ValidationException {

    private static final String MESSAGE = "Formato de contraseña no valido";

    public InvalidPasswordFormatException() {
        super(MESSAGE);
    }
}