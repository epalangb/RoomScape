package roomscape.es.roomscapefrontend.views.table_models;

import roomscape.es.roomscapefrontend.models.TEscapeRoom;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class TableModelListarEscapeRoom extends AbstractTableModel {

    private ArrayList<TEscapeRoom> scapeRooms;
    private String[] columnNames = {"ID", "Nombre", "Capacidad", "Duración", "Precio", "Estado"};

    public TableModelListarEscapeRoom() {
        scapeRooms = new ArrayList<TEscapeRoom>();
    }

    public void update(ArrayList<TEscapeRoom> scapeRooms) {
        this.scapeRooms = scapeRooms;
        fireTableDataChanged();
    }

    public TEscapeRoom getEscapeRoom(int id) {
        for (TEscapeRoom tEscapeRoom : scapeRooms) {
            if (tEscapeRoom.getId() == id) return tEscapeRoom;
        }
        return new TEscapeRoom();
    }

    @Override
    public int getRowCount() {
        return scapeRooms.size();
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
                return Integer.class;
            case 1:
                return String.class;
            case 2:
                return Integer.class;
            case 3:
                return Integer.class;
            case 4:
                return Double.class;
            case 5:
                return String.class;
            default:
                return String.class;
        }
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        Object object = null;

        switch (columnIndex) {
            case 0:
                object = scapeRooms.get(rowIndex).getId();
                break;
            case 1:
                object = scapeRooms.get(rowIndex).getNombre();
                break;
            case 2:
                object = scapeRooms.get(rowIndex).getCapacidadPersonas();
                break;
            case 3:
                object = scapeRooms.get(rowIndex).getDuracion();
                break;
            case 4:
                object = scapeRooms.get(rowIndex).getPrecio();
                break;
            case 5:
                object = scapeRooms.get(rowIndex).isActivo() ? "Activo" : "Inactivo";
                break;
        }
        return object;
    }
}
