package es.roomscape.roomscapefrontend.presentacion.factory_view;

import es.roomscape.roomscapefrontend.presentacion.MainView;
import es.roomscape.roomscapefrontend.presentacion.escape_room.AltaEscapeRoomBasicoView;
import es.roomscape.roomscapefrontend.presentacion.escape_room.EscapeRoomView;

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