package roomscape.es.roomscapebackend.negocio.exceptions.validations;

public class InvalidCapacityException extends ValidationException {

    private static final String MESSAGE = "Capacidad no válida. Se considera válida una capacidad mayor o igual que 1";

    public InvalidCapacityException() {
        super(MESSAGE);
    }
}
