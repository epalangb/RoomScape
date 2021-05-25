package roomscape.es.roomscapefrontend.views.reservas;

import roomscape.es.roomscapefrontend.app_controller.Context;
import roomscape.es.roomscapefrontend.app_controller.Event;
import roomscape.es.roomscapefrontend.app_controller.controller.Controller;
import roomscape.es.roomscapefrontend.models.TEscapeRoom;
import roomscape.es.roomscapefrontend.models.TReserva;
import roomscape.es.roomscapefrontend.utils.ComponentBuilder;
import roomscape.es.roomscapefrontend.utils.Validator;
import roomscape.es.roomscapefrontend.views.GenericView;
import roomscape.es.roomscapefrontend.views.View;
import roomscape.es.roomscapefrontend.views.table_models.TableModelListarEscapeRoom;
import roomscape.es.roomscapefrontend.views.table_models.TableModelListarReservas;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class ListReservationByHourDateView extends GenericView implements View {

    private static final String TITLE = "Listado de Reservas por Hora y fecha";

    private TableModelListarReservas tModelReservas;
    private JTable tableReservas;
    private JPanel jpanelBusqeda = new JPanel();
    private JButton listarButton = new JButton();
    private JTextField fechaTextField = new JTextField();
    private JLabel introduceLabel = new JLabel();

    private int selectedRow;

    public ListReservationByHourDateView() {
        initComponents();
    }

    @Override
    protected void initComponents() {

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        mainPanel.add(buildLeftPanel(Event.ListReservas), BorderLayout.WEST);
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

        tModelReservas = new TableModelListarReservas();
        tableReservas = new JTable(tModelReservas);

        jpanelBusqeda.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

        listarButton.setBackground(new java.awt.Color(0, 0, 0));
        listarButton.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        listarButton.setForeground(new java.awt.Color(255, 255, 255));
        listarButton.setText("Listar");
        listarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                listarButtonActionPerfomed(evt);
            }
        });

        introduceLabel.setText("Introduce Fecha:");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jpanelBusqeda);
        jpanelBusqeda.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
                jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(introduceLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(fechaTextField)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(listarButton, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
                jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel4Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(listarButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(introduceLabel)
                                        .addComponent(fechaTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap())
        );

        JToolBar toolbar = new JToolBar();
        toolbar.setFloatable(false);
        toolbar.setOrientation(SwingConstants.VERTICAL);
       // toolbar.add(editButton);

        centralPanel.add(jpanelBusqeda, BorderLayout.PAGE_START);
        centralPanel.add(new JScrollPane(tableReservas), BorderLayout.CENTER);
        centralPanel.add(toolbar, BorderLayout.EAST);

        return centralPanel;
    }

    private void listarButtonActionPerfomed(java.awt.event.ActionEvent evt) {
        try {
            Validator.DateFieldValidator(fechaTextField.getText(), "fecha de la reserva");
        } catch (Exception e) {
            ShowAlertMessage(e.getMessage());
            return;
        }
        Context c = new Context(fechaTextField.getText(), Event.ListReservas);
        Controller control = Controller.getInstance();
        control.action(c);
    }
    @Override
    public void update(Context context) {
      if (context.getEvent() == Event.ListReservas)
            tModelReservas.update((ArrayList<TReserva>) context.getData());
        else
            ShowAlertMessage(context.getData().toString());
    }
}
