package es.roomscape.roomscapefrontend.presentacion.controller;

import es.roomscape.roomscapefrontend.presentacion.Event;
import es.roomscape.roomscapefrontend.presentacion.command.Command;
import es.roomscape.roomscapefrontend.presentacion.command.CommandFactory;
import es.roomscape.roomscapefrontend.presentacion.command.dispatcher.Dispatcher;
import es.roomscape.roomscapefrontend.presentacion.factory_view.AbstractFactoryView;

public class ControllerImp extends Controller {

    public ControllerImp() {
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