package es.roomscape.roomscapefrontend.presentacion.command.dispatcher;

import es.roomscape.roomscapefrontend.presentacion.controller.Context;

public abstract class Dispatcher {

    private static Dispatcher instance;

    public static Dispatcher getInstance() {
        if (instance == null) {
            instance = new DispatcherImp();
        }
        return instance;
    }

    public abstract void createView(Context context);
}
