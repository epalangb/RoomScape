package roomscape.es.roomscapefrontend.views.reservas;

import roomscape.es.roomscapefrontend.app_controller.Context;
import roomscape.es.roomscapefrontend.app_controller.Event;
import roomscape.es.roomscapefrontend.app_controller.controller.Controller;
import roomscape.es.roomscapefrontend.models.TEscapeRoom;
import roomscape.es.roomscapefrontend.models.TReserva;
import roomscape.es.roomscapefrontend.utils.ComponentBuilder;
import roomscape.es.roomscapefrontend.utils.Configuration;
import roomscape.es.roomscapefrontend.utils.Validator;
import roomscape.es.roomscapefrontend.views.GenericView;
import roomscape.es.roomscapefrontend.views.View;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class AltaReservaView extends GenericView implements View {

    private Configuration config = Controller.getInstance().getConfiguration();

    private static final String TITLE = "Crear Reserva";

    private JLabel dateLbl;
    private JLabel durationLbl;
    private JLabel numberPersonLbl;
    private JLabel priceLbl;
    private JLabel nameLbl;


    private JTextField nameTxt;
    private JTextField dateTxt;
    private JTextField durationTxt;
    private JTextField numberPersonTxt;
    private JTextField priceTxt;

    private JPanel title;

    public AltaReservaView() {
        this.initComponents();
    }

    protected void initComponents() {

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        mainPanel.add(buildLeftPanel(Event.UpdateEscapeRoomView), BorderLayout.WEST);
        mainPanel.add(buildCentralPanel(), BorderLayout.CENTER);
        this.add(mainPanel);
        this.pack();
        this.setDefaultConfig();
    }

    @Override
    protected JPanel buildCentralPanel() {

        JPanel centralPanel = new JPanel();
        centralPanel.setAlignmentX(LEFT_ALIGNMENT);

        BorderLayout layout = new BorderLayout();
        centralPanel.setLayout(layout);

        nameLbl = ComponentBuilder.BuildLabelToForm("Nombre:");
        durationLbl = ComponentBuilder.BuildLabelToForm("Duración:");
        priceLbl = ComponentBuilder.BuildLabelToForm("Precio:");
        dateLbl = ComponentBuilder.BuildLabelToForm("Fecha de reserva: (dd/MM/yy HH:mm)");
        numberPersonLbl = ComponentBuilder.BuildLabelToForm("Número de personas:");

        nameTxt = ComponentBuilder.BuildTextFieldToForm();
        nameTxt.setBackground(Color.YELLOW);
        nameTxt.setEditable(false);
        durationTxt = ComponentBuilder.BuildTextFieldToForm();
        durationTxt.setBackground(Color.YELLOW);
        durationTxt.setEditable(false);
        priceTxt = ComponentBuilder.BuildTextFieldToForm();
        priceTxt.setBackground(Color.YELLOW);
        priceTxt.setEditable(false);
        dateTxt = ComponentBuilder.BuildTextFieldToForm();
        numberPersonTxt = ComponentBuilder.BuildTextFieldToForm();

        JPanel form = new JPanel(new GridLayout(10, 1));
        form.setBorder(new EmptyBorder(10, 60, 10, 60));

        form.add(nameLbl);
        form.add(nameTxt);
        form.add(durationLbl);
        form.add(durationTxt);
        form.add(priceLbl);
        form.add(priceTxt);
        form.add(dateLbl);
        form.add(dateTxt);
        form.add(numberPersonLbl);
        form.add(numberPersonTxt);

        centralPanel.add(BuildTitle(TITLE), BorderLayout.NORTH);
        centralPanel.add(form, BorderLayout.CENTER);
        centralPanel.add(BuildBottomPanel(), BorderLayout.SOUTH);

        return centralPanel;
    }

    public JToolBar BuildBottomPanel() {

        JToolBar toolbar = new JToolBar();
        toolbar.setFloatable(false);

        JButton cancelButton = ComponentBuilder.BuildButton("cancelar", "back");
        cancelButton.addActionListener(event -> {
            Context context = new Context(null, Event.AbrirEscapeRoomView);
            Controller.getInstance().action(context);
            this.dispose();
        });

        JButton acceptButton = ComponentBuilder.BuildButton("registrar", "update");
        acceptButton.addActionListener(event -> {

            TReserva tReserva = new TReserva();
            try {
                tReserva.setNombreEscapeRoom(Validator.EmptyFieldValidator(nameTxt.getText(), "nombre"));
                tReserva.setDuracion(Validator.NumericFieldValidator(durationTxt.getText(), "duración"));
                tReserva.setPrecio(Validator.DoubleFieldValidator(priceTxt.getText(), "precio"));
                tReserva.setFechaIni(Validator.DateFieldValidator(dateTxt.getText(), "fecha de la reserva"));
                tReserva.setParticipantes(Validator.NumericFieldValidator(numberPersonTxt.getText(), "participantes"));
            } catch (Exception e) {
                ShowAlertMessage(e.getMessage());
                return;
            }

            Context c = new Context(tReserva, Event.AltaReserva);
            Controller.getInstance().action(c);
        });

        toolbar.add(cancelButton);
        toolbar.add(acceptButton);
        toolbar.setLayout(new GridLayout(1, 2));

        return toolbar;
    }

    private void updateEscapeRoomData(TEscapeRoom tEscapeRoom) {
        this.nameTxt.setText(tEscapeRoom.getNombre());
        this.durationTxt.setText(String.valueOf(tEscapeRoom.getDuracion()));
        this.priceTxt.setText(String.valueOf(tEscapeRoom.getPrecio()));
    }

    @Override
    public void update(Context context) {
        switch (context.getEvent()) {
            case AbrirAltaReservaView:
                updateEscapeRoomData((TEscapeRoom) context.getData());
                break;
            case AltaReservaOK:
                ShowConfirmationMessage(context.getData().toString());
                break;
            case AltaReservaError:
                ShowErrorMessage(context.getData().toString());
                break;
        }
    }
}
