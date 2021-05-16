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
            case AltaEscapeRoomBasicoOK:
            case AltaEscapeRoomBasicoError:
                AbstractFactoryView.getInstance().createAltaEscapeRoomBasicoView().update(context);
                break;

            // Listar EscapeRooms
            case ListEscapeRoom:
            case ListEscapeRoomError:
                AbstractFactoryView.getInstance().createListarEscapeRoomBasicoView().update(context);
                break;

            // EscapeRoom Update
            case UpdateEscapeRoomView:
            case UpdateEscapeRoomOK:
            case UpdateEscapeRoomError:
                AbstractFactoryView.getInstance().createUpdateEscapeRoomView().update(context);
                break;
            case AbrirListReservasView:
                AbstractFactoryView.getInstance().createListarReservasHourDateView();
                break;
            //Listar Reservas
            case ListReservas:
            case ListReservasError:
                AbstractFactoryView.getInstance().createListarReservasHourDateView().update(context);
                break;


            // Error
            case CommandError:
                AbstractFactoryView.getInstance().createVistaError("No se puede ejecutar el comando");
                break;
        }
    }
}
