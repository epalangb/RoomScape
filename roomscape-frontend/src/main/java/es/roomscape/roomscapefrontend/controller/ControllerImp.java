package es.roomscape.roomscapefrontend.controller;

import es.roomscape.roomscapefrontend.command.Command;
import es.roomscape.roomscapefrontend.command.CommandFactory;
import es.roomscape.roomscapefrontend.command.dispatcher.Dispatcher;
import es.roomscape.roomscapefrontend.factory_view.AbstractFactoryView;

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