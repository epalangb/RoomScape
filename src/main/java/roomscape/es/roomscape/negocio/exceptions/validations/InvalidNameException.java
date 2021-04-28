package roomscape.es.roomscape.negocio.exceptions.validations;

public class InvalidNameException extends ValidationException {

    private static final String MESSAGE = "Nombre no válido por existir en la base de datos";

    public InvalidNameException() {
        super(MESSAGE);
    }
}
