package roomscape.es.roomscape.Presentación.Command;

import roomscape.es.roomscape.Presentación.Eventos;

public abstract class CommandFactory {

    public static CommandFactory instance;

    public static CommandFactory getInstance(){
        if(instance==null) {
            instance= new CommandFactoryImp();
            instance.createAvailableCommands();
        }
        return instance;
    }

    protected abstract void createAvailableCommands();
    public abstract Command parse(Eventos evento);
}
