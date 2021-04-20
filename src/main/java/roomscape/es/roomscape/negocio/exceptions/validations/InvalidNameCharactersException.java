package roomscape.es.roomscape.negocio.exceptions.validations;

public class InvalidNameCharactersException extends ValidationException {

    private static final String MESSAGE = "El nombre no es válido porque contiene caracteres no permitidos";

    public InvalidNameCharactersException() {
        super(MESSAGE);
    }
}
