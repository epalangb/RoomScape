package es.roomscape.roomscapefrontend.presentacion.command;

import es.roomscape.roomscapefrontend.presentacion.Event;

public abstract class CommandFactory {

    public static CommandFactory instance;

    public static CommandFactory getInstance() {
        if (instance == null) {
            instance = new CommandFactoryImp();
            instance.createAvailableCommands();
        }
        return instance;
    }

    protected abstract void createAvailableCommands();

    public abstract Command parse(Event event);
}
