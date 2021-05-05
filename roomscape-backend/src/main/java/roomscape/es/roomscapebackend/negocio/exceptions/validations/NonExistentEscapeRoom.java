package roomscape.es.roomscapebackend.negocio.exceptions.validations;

public class NonExistentEscapeRoom extends ValidationException {

    private static final String MESSAGE = "No se puede realizar la operación porque el escape room con id: %s no existe";

    public NonExistentEscapeRoom(int id) {
        super(String.format(MESSAGE, id));
    }
}
