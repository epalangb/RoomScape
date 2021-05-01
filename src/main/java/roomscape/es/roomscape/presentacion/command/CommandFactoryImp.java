package roomscape.es.roomscape.presentacion.command;

import roomscape.es.roomscape.presentacion.command.escape_room.AltaEscapeRoomBasicoCommand;
import roomscape.es.roomscape.presentacion.Eventos;
import roomscape.es.roomscape.presentacion.command.escape_room.ListarEscapeRoomCommand;

import java.util.HashMap;

public class CommandFactoryImp extends CommandFactory{

    private static HashMap<Eventos,Command> availableCommands= new HashMap<Eventos,Command>();

    @Override
    protected void createAvailableCommands() {

        //AGC
        availableCommands.put(Eventos.AltaEscapeRoomBasicoOK, new AltaEscapeRoomBasicoCommand());
        availableCommands.put(Eventos.ListarEscapeRoomOK, new ListarEscapeRoomCommand());
    }


    @Override
    public Command parse(Eventos evento) {
        return availableCommands.get(evento);
    }

}