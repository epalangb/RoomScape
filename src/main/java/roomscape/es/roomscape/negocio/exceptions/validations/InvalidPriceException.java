package roomscape.es.roomscape.negocio.exceptions.validations;

public class InvalidPriceException extends ValidationException {

    private static final String MESSAGE = "Precio no válido, se considera válido un precio mayor o igual que 0";

    public InvalidPriceException() {
        super(MESSAGE);
    }
}
