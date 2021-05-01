package roomscape.es.roomscapefrontend.app_controller.dispatcher;

import roomscape.es.roomscapefrontend.app_controller.Context;
import roomscape.es.roomscapefrontend.views.factory_view.AbstractFactoryView;

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
            case AbrirListarEscapeRoomView:
                AbstractFactoryView.getInstance().createListarEscapeRoomBasicoView();
                break;
            case AltaEscapeRoomBasicoOK:
            case AltaEscapeRoomBasicoError:
                AbstractFactoryView.getInstance().createAltaEscapeRoomBasicoView().update(context);
                break;

            // Listar
            case ListarEscapeRoomOK:
            case ListarEscapeRoomError:
                AbstractFactoryView.getInstance().createListarEscapeRoomBasicoView().update(context);
                break;

            // Error
            case CommandError:
                AbstractFactoryView.getInstance().createVistaError("No se puede ejecutar el comando");
                break;
        }
    }
}
