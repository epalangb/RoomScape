package roomscape.es.roomscapefrontend.views.factory_view;

import roomscape.es.roomscapefrontend.views.ErrorView;
import roomscape.es.roomscapefrontend.views.MainView;
import roomscape.es.roomscapefrontend.views.escape_room.AltaEscapeRoomBasicoView;
import roomscape.es.roomscapefrontend.views.escape_room.ListEscapeRoomView;
import roomscape.es.roomscapefrontend.views.escape_room.UpdateEscapeRoomView;
import roomscape.es.roomscapefrontend.views.reservas.AltaReservaView;
import roomscape.es.roomscapefrontend.views.reservas.ListReservationByHourDateView;

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
        if (v == null || !(v instanceof ErrorView)) {
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
    public UpdateEscapeRoomView createUpdateEscapeRoomView() {
        if (v == null || !(v instanceof UpdateEscapeRoomView)) {
            v = new UpdateEscapeRoomView();
        }
        return (UpdateEscapeRoomView) v;
    }

    @Override
    public ListEscapeRoomView createListarEscapeRoomBasicoView() {
        if (v == null || !(v instanceof ListEscapeRoomView)) {
            v = new ListEscapeRoomView();
        }
        return (ListEscapeRoomView) v;
    }


    @Override
    public AltaReservaView createAltaReservaView() {
        if(v == null || !(v instanceof  AltaReservaView)){
            v = new AltaReservaView();
        }
        return (AltaReservaView) v;
    }

    @Override
    public ListReservationByHourDateView createListarReservasHourDateView() {
        if (v == null || !(v instanceof ListReservationByHourDateView)) {
            v = new ListReservationByHourDateView();
        }
        return (ListReservationByHourDateView) v;    }

    public MainView createMainView() {
        if (v == null || !(v instanceof MainView)) {
            v = new MainView();
        }
        return (MainView) v;
    }
}