package roomscape.es.roomscape.negocio.exceptions.validations;

public class InvalidNameException extends ValidationException {

    private static final String MESSAGE = "El nombre no es válido porque ya existe en la base de datos";

    public InvalidNameException() {
        super(MESSAGE);
    }
}
