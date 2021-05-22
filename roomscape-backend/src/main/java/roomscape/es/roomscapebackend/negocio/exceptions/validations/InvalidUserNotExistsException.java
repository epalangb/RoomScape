package roomscape.es.roomscapebackend.negocio.exceptions.validations;

public class InvalidUserNotExistsException extends ValidationException {

    private static final String MESSAGE = "El usuario no existe en la base de datos";

    public InvalidUserNotExistsException() {
        super(MESSAGE);
    }

}
