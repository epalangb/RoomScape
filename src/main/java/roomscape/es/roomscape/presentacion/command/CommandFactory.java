package roomscape.es.roomscape.presentacion.command;

import roomscape.es.roomscape.presentacion.Eventos;

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
