package roomscape.es.roomscape.negocio.exceptions.validations;

public class NonExistentEscapeRoom extends ValidationException {

    private static final String MESSAGE = "The Escape room does not exist";

    public NonExistentEscapeRoom() {
        super(MESSAGE);
    }

}
