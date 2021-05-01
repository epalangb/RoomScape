package roomscape.es.roomscape.negocio.exceptions.validations;

public class InvalidEscapeRoomException extends ValidationException {
    private static final String MESSAGE = "La Escape Room seleccionada no existe";

    public InvalidEscapeRoomException() {
        super(MESSAGE);
    }
}