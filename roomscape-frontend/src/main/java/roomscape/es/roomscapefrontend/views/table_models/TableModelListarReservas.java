package roomscape.es.roomscapefrontend.views.table_models;

import roomscape.es.roomscapefrontend.models.TEscapeRoom;
import roomscape.es.roomscapefrontend.models.TReserva;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.Calendar;

public class TableModelListarReservas extends AbstractTableModel {
    private ArrayList<TReserva> reservas;
    private String[] columnNames = {"Fecha Inicio", "Nombre Escape Room", "Participantes", "ID"};

    public TableModelListarReservas() {
        reservas = new ArrayList<TReserva>();
    }

    public void update(ArrayList<TReserva> reservas) {
        this.reservas = reservas;
        fireTableDataChanged();
    }

    public TReserva getReserva(int id) {
        for (TReserva tReserva : reservas) {
            if (tReserva.getId() == id) return tReserva;
        }
        return new TReserva();
    }

    @Override
    public int getRowCount() {
        return reservas.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return columnNames[columnIndex];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return String.class;
            case 1:
                return String.class;
            case 2:
                return String.class;
            case 3:
                return Integer.class;
            default:
                return String.class;
        }
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        Object object = null;

        switch (columnIndex) {
            case 0:
                object = reservas.get(rowIndex).getFechaFin().getTime().toString();
                break;
            case 1:
                object = reservas.get(rowIndex).getNombreEscapeRoom();
                break;
            case 2:
                object = reservas.get(rowIndex).getParticipantes();
                break;
            case 3:
                object = reservas.get(rowIndex).getId();
        }
        return object;
    }
}
