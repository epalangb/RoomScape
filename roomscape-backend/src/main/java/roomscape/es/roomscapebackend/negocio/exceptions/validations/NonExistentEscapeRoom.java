package roomscape.es.roomscapebackend.negocio.exceptions.validations;

public class NonExistentEscapeRoom extends ValidationException {

    private static final String MESSAGE = "El escape room que se quiere actualizar no existe";

    public NonExistentEscapeRoom() {
        super(MESSAGE);
    }

}
