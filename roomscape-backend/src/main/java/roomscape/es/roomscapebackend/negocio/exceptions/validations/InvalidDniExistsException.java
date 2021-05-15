package roomscape.es.roomscapebackend.negocio.exceptions.validations;

public class InvalidDniExistsException extends ValidationException{
    private static final String MESSAGE = "Dni no válido por existir en la base de datos";

    public InvalidDniExistsException() {
        super(MESSAGE);
    }
}
