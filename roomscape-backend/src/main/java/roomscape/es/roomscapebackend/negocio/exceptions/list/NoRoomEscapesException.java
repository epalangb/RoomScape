package roomscape.es.roomscapebackend.negocio.exceptions.list;

public class NoRoomEscapesException extends EmptyListException{
    private static final String MESSAGE = "La lista esta vacía, no existen Escape Rooms";

    public NoRoomEscapesException() {
        super(MESSAGE);
    }
}
