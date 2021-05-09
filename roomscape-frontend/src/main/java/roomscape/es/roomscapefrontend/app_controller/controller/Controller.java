package roomscape.es.roomscapefrontend.app_controller.controller;

import roomscape.es.roomscapefrontend.app_controller.Context;
import roomscape.es.roomscapefrontend.utils.Configuration;

public abstract class Controller {

    private static Controller controller;
    private Configuration configuration;

    public static Controller getInstance() {
        if (controller == null) {
            controller = new ControllerImp();
        }
        return controller;
    }

    public abstract void action(Context context);

    public abstract void startApp();

    public Configuration getConfiguration() {
        return configuration;
    }

    public void setConfiguration(Configuration configuration) {
        this.configuration = configuration;
    }
}