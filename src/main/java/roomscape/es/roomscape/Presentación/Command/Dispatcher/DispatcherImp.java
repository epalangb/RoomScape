package roomscape.es.roomscape.Presentación.Command.Dispatcher;

import roomscape.es.roomscape.Presentación.Controller.Context;
import roomscape.es.roomscape.Presentación.FactoryView.AbstractFactoryView;

import javax.swing.*;

public class DispatcherImp extends Dispatcher {

    @Override
    public void createView(Context contexto) {
        switch (contexto.getEvento()) {
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
                AbstractFactoryView.getInstance().createAltaEscapeRoomBasicoView().update(contexto);
                break;
            case CommandError:
                AbstractFactoryView.getInstance().createVistaError("No se puede ejecutar el comando");
                break;
        }
    }
}
