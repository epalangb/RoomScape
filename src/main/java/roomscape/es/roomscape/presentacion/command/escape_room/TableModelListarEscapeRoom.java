package roomscape.es.roomscape.presentacion.command.escape_room;

import roomscape.es.roomscape.negocio.escape_room.TEscapeRoom;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TableModelListarEscapeRoom extends AbstractTableModel {

    private List<TEscapeRoom> scapeRooms;
    private String[] columnNames = { "Nombre", "Capacidad", "Duración", "Precio" };

    public TableModelListarEscapeRoom() {
        scapeRooms = new ArrayList<TEscapeRoom>();
    }

    public void uptade(ArrayList<TEscapeRoom> scaperooms)
    {
        this.scapeRooms = new ArrayList<TEscapeRoom>();
        Iterator<TEscapeRoom> it = scaperooms.iterator();
        while(it.hasNext())
        {
            TEscapeRoom next = it.next();
            this.scapeRooms.add(next);
        }
        fireTableStructureChanged();
        fireTableDataChanged();
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
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return scapeRooms.get(rowIndex).getNombre();
            case 1:
                return scapeRooms.get(rowIndex).getCapacidadPersonas();
            case 2:
                return scapeRooms.get(rowIndex).getDuracion();
            case 3:
                return scapeRooms.get(rowIndex).getPrecio();
            default:
                return "ERROR";
        }
    }
}
