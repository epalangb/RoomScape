package roomscape.es.roomscape.negocio.exceptions.validations;

public class InvalidPriceException extends ValidationException {

    private static final String MESSAGE = "La capacidad no es válida por no ser mayor que 0";

    public InvalidPriceException() {
        super(MESSAGE);
    }
}
