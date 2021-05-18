package roomscape.es.roomscapebackend.negocio.exceptions.validations;

public class InvalidDniException extends ValidationException{
    private static final String MESSAGE = "Dni con formato no válido";

    public InvalidDniException() {
        super(MESSAGE);
    }
}
