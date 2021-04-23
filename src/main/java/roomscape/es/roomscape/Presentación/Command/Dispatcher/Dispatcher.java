package roomscape.es.roomscape.Presentación.Command.Dispatcher;

import roomscape.es.roomscape.Presentación.Controller.Context;

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
