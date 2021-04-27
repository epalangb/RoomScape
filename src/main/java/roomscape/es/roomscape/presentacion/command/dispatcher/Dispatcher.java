package roomscape.es.roomscape.presentacion.command.dispatcher;

import roomscape.es.roomscape.presentacion.controller.Context;

public abstract class Dispatcher {

    private static Dispatcher instance;

    public static Dispatcher getInstance() {
        if(instance==null) {
            instance= new DispatcherImp();
        }
        return instance;
    }

    public abstract void createView(Context contexto);
}
