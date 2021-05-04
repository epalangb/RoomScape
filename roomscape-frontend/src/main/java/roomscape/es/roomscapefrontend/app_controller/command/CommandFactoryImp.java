package roomscape.es.roomscapefrontend.app_controller.command;

import roomscape.es.roomscapefrontend.app_controller.Event;
import roomscape.es.roomscapefrontend.app_controller.command.escape_room.AltaEscapeRoomBasicoCommand;
import roomscape.es.roomscapefrontend.app_controller.command.escape_room.BajaEscapeRoomCommand;
import roomscape.es.roomscapefrontend.app_controller.command.escape_room.ListarEscapeRoomCommand;
import roomscape.es.roomscapefrontend.app_controller.command.escape_room.UpdateEscapeRoomCommand;

import java.util.HashMap;

public class CommandFactoryImp extends CommandFactory {

    private static HashMap<Event, Command> availableCommands = new HashMap<Event, Command>();

    @Override
    protected void createAvailableCommands() {
        availableCommands.put(Event.AltaEscapeRoomBasicoOK, new AltaEscapeRoomBasicoCommand());
        availableCommands.put(Event.ListEscapeRoom, new ListarEscapeRoomCommand());
        availableCommands.put(Event.UpdateEscapeRoom, new UpdateEscapeRoomCommand());
        availableCommands.put(Event.BajaEscapeRoom, new BajaEscapeRoomCommand());
    }

    @Override
    public Command parse(Event event) {
        return availableCommands.get(event);
    }
}