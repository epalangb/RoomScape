package roomscape.es.roomscape.presentacion.command.dispatcher;

import roomscape.es.roomscape.presentacion.controller.Context;
import roomscape.es.roomscape.presentacion.factory_view.AbstractFactoryView;

public class DispatcherImp extends Dispatcher {

    @Override
    public void createView(Context contexto) {
        switch (contexto.getEvento()) {
            // Abrir
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
                AbstractFactoryView.getInstance().createAltaEscapeRoomBasicoView().update(contexto);
                break;

            // Listar
            case ListarEscapeRoomOK:
            case ListarEscapeRoomError:
                AbstractFactoryView.getInstance().createListarEscapeRoomBasicoView().update(contexto);
                break;

            // Error
            case CommandError:
                AbstractFactoryView.getInstance().createVistaError("No se puede ejecutar el comando");
                break;
        }
    }
}