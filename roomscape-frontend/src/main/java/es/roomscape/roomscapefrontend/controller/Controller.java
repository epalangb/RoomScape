package es.roomscape.roomscapefrontend.controller;

import es.roomscape.roomscapefrontend.Configuration;

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

    public void setConfiguration(Configuration configuration) {
        this.configuration = configuration;
    }

    public Configuration getConfiguration() {
        return configuration;
    }
}