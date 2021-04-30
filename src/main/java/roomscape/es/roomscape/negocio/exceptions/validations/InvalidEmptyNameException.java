package roomscape.es.roomscape.negocio.exceptions.validations;

public class InvalidEmptyNameException extends ValidationException {

    private static final String MESSAGE = "Nombre no válido por ser vacío";

    public InvalidEmptyNameException() {
        super(MESSAGE);
    }
}
