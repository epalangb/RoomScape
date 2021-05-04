package roomscape.es.roomscapefrontend.views.escape_room;

import roomscape.es.roomscapefrontend.app_controller.Context;
import roomscape.es.roomscapefrontend.app_controller.Event;
import roomscape.es.roomscapefrontend.app_controller.controller.Controller;
import roomscape.es.roomscapefrontend.models.TEscapeRoom;
import roomscape.es.roomscapefrontend.utils.ComponentBuilder;
import roomscape.es.roomscapefrontend.views.GenericView;
import roomscape.es.roomscapefrontend.views.View;
import roomscape.es.roomscapefrontend.views.table_models.TableModelListarEscapeRoom;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;

public class ListEscapeRoomView extends GenericView implements View {

    private static final String TITLE = "Listado de Escape Rooms";
    private static final String BUTTON_EDIT = "editar";
    private static final String BUTTON_BAJA = "Dar de baja";
    private static final String CONFIRMATION_MESSAGE = "¿Estás seguro de dar de baja el escape room con id ";
    private static final String SUCCESS_MESSAGE = "Se ha dado de baja correctamente el escape room:";

    private TableModelListarEscapeRoom tModelEscapeRooms;
    private JTable tableEscapeRooms;

    public ListEscapeRoomView() {
        initComponents();
    }

    @Override
    protected void initComponents() {

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        mainPanel.add(buildLeftPanel(Event.ListEscapeRoom), BorderLayout.WEST);
        mainPanel.add(buildCentralPanel(), BorderLayout.CENTER);
        this.add(mainPanel);
        this.pack();
        this.setDefaultConfig();
    }

    @Override
    protected JPanel buildCentralPanel() {

        JPanel centralPanel = new JPanel();
        centralPanel.setLayout(new BorderLayout());
        centralPanel.setBorder(new EmptyBorder(10, 20, 10, 10));

        tModelEscapeRooms = new TableModelListarEscapeRoom();
        tableEscapeRooms = new JTable(tModelEscapeRooms);
        tableEscapeRooms.getTableHeader().setReorderingAllowed(true);
        tableEscapeRooms.setAutoCreateRowSorter(true);

        JButton editButton = ComponentBuilder.BuildButton(BUTTON_EDIT, EDIT_ICON);
        editButton.setEnabled(false);

        JButton bajaButton = ComponentBuilder.BuildButton(BUTTON_BAJA, BAJA_ICON);
        bajaButton.setEnabled(false);

        JToolBar toolbar = new JToolBar();
        toolbar.setFloatable(false);
        toolbar.setOrientation(SwingConstants.VERTICAL);
        toolbar.add(editButton);
        toolbar.add(bajaButton);

        tableEscapeRooms.getSelectionModel().addListSelectionListener(e -> {
            bajaButton.setEnabled(true);
            bajaButton.addActionListener(event -> {
                int id = (int) tableEscapeRooms.getValueAt(e.getFirstIndex(), 0);
                int response = ShowConfirmationMessage(CONFIRMATION_MESSAGE + id + "?");
                if (response == 0) {
                    Context context = new Context(id, Event.BajaEscapeRoom);
                    Controller.getInstance().action(context);
                    close();
                }
            });
            editButton.setEnabled(true);
            editButton.addActionListener(event -> {
                int id = (int) tableEscapeRooms.getValueAt(e.getFirstIndex(), 0);
                Context context = new Context(tModelEscapeRooms.getEscapeRoom(id), Event.UpdateEscapeRoomView);
                Controller.getInstance().action(context);
                close();
            });
        });

        centralPanel.add(BuildTitle(TITLE), BorderLayout.NORTH);
        centralPanel.add(new JScrollPane(tableEscapeRooms), BorderLayout.CENTER);
        centralPanel.add(toolbar, BorderLayout.EAST);

        return centralPanel;
    }

    @Override
    public void update(Context context) {
        if (context.getEvent() == Event.ListEscapeRoom)
            tModelEscapeRooms.update((ArrayList<TEscapeRoom>) context.getData());
        else if (context.getEvent() == Event.BajaEscapeRoomOK)
            ShowSuccessMessage(SUCCESS_MESSAGE);
        else if(context.getEvent() == Event.BajaEScapeRoomError)
            ShowErrorMessage(context.getData().toString());
        else
            ShowAlertMessage(context.getData().toString());
    }
}
