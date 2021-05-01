package es.roomscape.roomscapefrontend.factory_view;

import es.roomscape.roomscapefrontend.views.MainView;
import es.roomscape.roomscapefrontend.views.escape_room.AltaEscapeRoomBasicoView;
import es.roomscape.roomscapefrontend.views.escape_room.EscapeRoomView;

import javax.swing.*;

public abstract class AbstractFactoryView {

    private static AbstractFactoryView instance;

    protected static JFrame v;


    public synchronized static AbstractFactoryView getInstance() {
        if (instance == null) {
            instance = new FactoryViewImp();
        }
        return instance;
    }

    public abstract MainView createMainView();

    public abstract EscapeRoomView createViewEscapeRoomBasico();

    public abstract AltaEscapeRoomBasicoView createAltaEscapeRoomBasicoView();

    public abstract JFrame createVistaError(String s);
}