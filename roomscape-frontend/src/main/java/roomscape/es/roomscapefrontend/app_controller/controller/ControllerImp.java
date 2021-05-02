package roomscape.es.roomscapefrontend.app_controller.controller;

import roomscape.es.roomscapefrontend.app_controller.Context;
import roomscape.es.roomscapefrontend.app_controller.Event;
import roomscape.es.roomscapefrontend.app_controller.command.Command;
import roomscape.es.roomscapefrontend.app_controller.command.CommandFactory;
import roomscape.es.roomscapefrontend.app_controller.dispatcher.Dispatcher;
import roomscape.es.roomscapefrontend.views.factory_view.AbstractFactoryView;

public class ControllerImp extends Controller {

    public ControllerImp() {
    }

    public void startApp() {
        AbstractFactoryView.getInstance().createMainView();
    }

    public void action(Context context) {

        Command cmd = CommandFactory.getInstance().parse(context.getEvent());

        Context newContext = null;

        if (cmd != null) {
            try {
                newContext = cmd.execute(context.getData());
            } catch (Exception e) {
                newContext = new Context(null, Event.CommandError);
                Dispatcher.getInstance().createView(newContext);
            }
        }
        if (newContext != null) {
            Dispatcher.getInstance().createView(newContext);
        } else {
            Dispatcher.getInstance().createView(context);
        }
    }
}