package es.roomscape.roomscapefrontend.command;

import es.roomscape.roomscapefrontend.controller.Event;
import es.roomscape.roomscapefrontend.command.escape_room.AltaEscapeRoomBasicoCommand;

import java.util.HashMap;

public class CommandFactoryImp extends CommandFactory {

    private static HashMap<Event, Command> availableCommands = new HashMap<Event, Command>();

    @Override
    protected void createAvailableCommands() {
        availableCommands.put(Event.AltaEscapeRoomBasicoOK, new AltaEscapeRoomBasicoCommand());
    }

    @Override
    public Command parse(Event event) {
        return availableCommands.get(event);
    }
}