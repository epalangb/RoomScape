package roomscape.es.roomscape.negocio.exceptions.validations;

public class InvalidNameCharactersException extends ValidationException {

    private static final String MESSAGE = "Nombre no válido por contener los siguientes caracteres no permitidos: ";

    public InvalidNameCharactersException(String invalidCharacters) {
        super(MESSAGE + invalidCharacters);
    }
}
