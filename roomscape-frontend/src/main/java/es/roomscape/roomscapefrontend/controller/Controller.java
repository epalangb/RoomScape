package es.roomscape.roomscapefrontend.controller;

public abstract class Controller {

    private static Controller controller;

    public static Controller getInstance() {
        if (controller == null) {
            controller = new ControllerImp();
        }
        return controller;
    }

    public abstract void action(Context context);

}