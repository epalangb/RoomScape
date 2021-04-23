package roomscape.es.roomscape.Presentación.FactoryView;


import roomscape.es.roomscape.Presentación.EscapeRoom.AltaEscapeRoomBasicoView;
import roomscape.es.roomscape.Presentación.EscapeRoom.EscapeRoomView;
import roomscape.es.roomscape.Presentación.MainView;

import javax.swing.*;

public abstract class AbstractFactoryView {

    private static AbstractFactoryView fVista;

    protected static JFrame v;


    public synchronized static AbstractFactoryView getInstance() {
        // begin-user-code
        // TODO Auto-generated method stub
        if(fVista == null) {
            fVista = new FactoryViewImp();
        }
        return fVista;
        // end-user-code
    }

    public abstract MainView createMainView();

    public abstract EscapeRoomView createViewEscapeRoomBasico();
    public abstract AltaEscapeRoomBasicoView createAltaEscapeRoomBasicoView();

    public abstract JFrame createVistaError(String s);
}