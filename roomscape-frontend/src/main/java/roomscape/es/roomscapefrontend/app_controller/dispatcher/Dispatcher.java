package roomscape.es.roomscapefrontend.app_controller.dispatcher;

import roomscape.es.roomscapefrontend.app_controller.Context;

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
