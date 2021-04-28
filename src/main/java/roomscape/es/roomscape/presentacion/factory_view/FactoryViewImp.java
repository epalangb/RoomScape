package roomscape.es.roomscape.presentacion.factory_view;


import roomscape.es.roomscape.presentacion.ErrorView;
import roomscape.es.roomscape.presentacion.escape_room.AltaEscapeRoomBasicoView;
import roomscape.es.roomscape.presentacion.escape_room.EscapeRoomView;
import roomscape.es.roomscape.presentacion.MainView;
import roomscape.es.roomscape.presentacion.escape_room.ListarEscapeRoomView;

import javax.swing.*;

public class FactoryViewImp extends AbstractFactoryView {


    public EscapeRoomView createViewEscapeRoomBasico() {
        if (v == null || !(v instanceof EscapeRoomView)) {
            v = new EscapeRoomView();
        }
        return (EscapeRoomView) v;
    }

    @Override
    public JFrame createVistaError(String errorMsg) {
        if(v == null || !(v instanceof ErrorView)) {
            v = new ErrorView(errorMsg);
        }
        return (ErrorView) v;
    }

    public AltaEscapeRoomBasicoView createAltaEscapeRoomBasicoView() {
        if (v == null || !(v instanceof AltaEscapeRoomBasicoView)) {
            v = new AltaEscapeRoomBasicoView();
        }
        return (AltaEscapeRoomBasicoView) v;
    }

    @Override
    public ListarEscapeRoomView createListarEscapeRoomBasicoView() {
        if (v == null || !(v instanceof ListarEscapeRoomView)) {
            v = new ListarEscapeRoomView();
        }
        return (ListarEscapeRoomView) v;
    }

    public MainView createMainView() {
        if (v == null || !(v instanceof MainView)) {
            v = new MainView();
        }
        return (MainView) v;
    }
}