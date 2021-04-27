package roomscape.es.roomscape.presentacion.command;

import roomscape.es.roomscape.presentacion.command.escape_room.AltaEscapeRoomBasicoCommand;
import roomscape.es.roomscape.presentacion.Eventos;

import java.util.HashMap;

public class CommandFactoryImp extends CommandFactory{

    private static HashMap<Eventos,Command> availableCommands= new HashMap<Eventos,Command>();

    @Override
    protected void createAvailableCommands() {

        //AGC
        availableCommands.put(Eventos.AltaEscapeRoomBasicoOK, new AltaEscapeRoomBasicoCommand());
    }


    @Override
    public Command parse(Eventos evento) {
        return availableCommands.get(evento);
    }

}