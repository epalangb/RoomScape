package roomscape.es.roomscape.Presentación.Command.Dispatcher;

import roomscape.es.roomscape.Presentación.Controller.Context;
import roomscape.es.roomscape.Presentación.FactoryView.AbstractFactoryView;

import javax.swing.*;

public class DispatcherImp extends Dispatcher{

    @Override
    public void createView(Context contexto) {
        JFrame vista=null;
        switch(contexto.getEvento()) {
            case AbrirMainView:
                vista= AbstractFactoryView.getInstance().createMainView();
                break;
            case AbrirEscapeRoomView:
                vista= AbstractFactoryView.getInstance().createViewEscapeRoomBasico();
                break;
            case AbrirAltaEscapeRoomBasicoView:
                vista= AbstractFactoryView.getInstance().createAltaEscapeRoomBasicoView();
                break;
            case CommandError:
                vista = AbstractFactoryView.getInstance().createVistaError("No se puede ejecutar el comando");
                break;

        }
    }
}
