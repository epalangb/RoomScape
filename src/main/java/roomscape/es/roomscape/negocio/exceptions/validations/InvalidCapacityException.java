package roomscape.es.roomscape.negocio.exceptions.validations;

public class InvalidCapacityException extends ValidationException {

    private static final String MESSAGE = "El precio no es válido por no ser mayor o igual que 0";

    public InvalidCapacityException() {
        super(MESSAGE);
    }
}
