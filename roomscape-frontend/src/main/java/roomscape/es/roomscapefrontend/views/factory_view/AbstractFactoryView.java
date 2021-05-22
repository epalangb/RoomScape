package roomscape.es.roomscapefrontend.views.factory_view;

import roomscape.es.roomscapefrontend.views.MainView;
import roomscape.es.roomscapefrontend.views.escape_room.AltaEscapeRoomBasicoView;
import roomscape.es.roomscapefrontend.views.escape_room.*;
import roomscape.es.roomscapefrontend.views.escape_room.ListEscapeRoomView;
import roomscape.es.roomscapefrontend.views.escape_room.UpdateEscapeRoomView;
import roomscape.es.roomscapefrontend.views.reservas.AltaReservaView;

import javax.swing.*;

public abstract class AbstractFactoryView {

    protected static JFrame v;
    private static AbstractFactoryView instance;

    public synchronized static AbstractFactoryView getInstance() {
        if (instance == null) {
            instance = new FactoryViewImp();
        }
        return instance;
    }

    public abstract MainView createMainView();

    public abstract EscapeRoomView createViewEscapeRoomBasico();

    public abstract AltaEscapeRoomBasicoView createAltaEscapeRoomBasicoView();

    public abstract UpdateEscapeRoomView createUpdateEscapeRoomView();

    public abstract ListEscapeRoomView createListarEscapeRoomBasicoView();

    public abstract AltaReservaView createAltaReservaView();

    public abstract JFrame createVistaError(String s);
}