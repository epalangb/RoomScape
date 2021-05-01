package es.roomscape.roomscapefrontend.command.dispatcher;

import es.roomscape.roomscapefrontend.controller.Context;
import es.roomscape.roomscapefrontend.factory_view.AbstractFactoryView;

public class DispatcherImp extends Dispatcher {

    @Override
    public void createView(Context context) {
        switch (context.getEvent()) {
            case AbrirMainView:
                AbstractFactoryView.getInstance().createMainView();
                break;
            case AbrirEscapeRoomView:
                AbstractFactoryView.getInstance().createViewEscapeRoomBasico();
                break;
            case AbrirAltaEscapeRoomBasicoView:
                AbstractFactoryView.getInstance().createAltaEscapeRoomBasicoView();
                break;
            case AltaEscapeRoomBasicoOK:
            case AltaEscapeRoomBasicoError:
                AbstractFactoryView.getInstance().createAltaEscapeRoomBasicoView().update(context);
                break;
            case CommandError:
                AbstractFactoryView.getInstance().createVistaError("No se puede ejecutar el comando");
                break;
        }
    }
}
