package roomscape.es.roomscape.Presentación.Command;

import roomscape.es.roomscape.Presentación.Command.RoomScapeCommands.AltaEscapeRoomBasicoCommand;
import roomscape.es.roomscape.Presentación.Eventos;

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