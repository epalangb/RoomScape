package roomscape.es.roomscapefrontend.views.escape_room;

import roomscape.es.roomscapefrontend.app_controller.Context;
import roomscape.es.roomscapefrontend.app_controller.Event;
import roomscape.es.roomscapefrontend.app_controller.controller.Controller;
import roomscape.es.roomscapefrontend.models.TEscapeRoom;
import roomscape.es.roomscapefrontend.utils.ComponentBuilder;
import roomscape.es.roomscapefrontend.utils.Validator;
import roomscape.es.roomscapefrontend.views.GenericView;
import roomscape.es.roomscapefrontend.views.View;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class UpdateEscapeRoomView extends GenericView implements View {

    private static final String TITLE = "Modificar Escape Room";
    private static final String CONFIRMATION_MESSAGE = "¿Estás seguro de actualizar el escape room con los siguientes datos?";
    private static final String SUCCESS_MESSAGE = "Se ha actualizado correctamente el escape room:";

    private JLabel idLbl;
    private JLabel nameLbl;
    private JLabel capacityLbl;
    private JLabel durationLbl;
    private JLabel priceLbl;

    private JTextField idTxt;
    private JTextField nameTxt;
    private JTextField capacityTxt;
    private JTextField durationTxt;
    private JTextField priceTxt;

    public UpdateEscapeRoomView() {
        this.initComponents();
    }

    @Override
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

        idLbl = ComponentBuilder.BuildLabelToForm("ID:");
        nameLbl = ComponentBuilder.BuildLabelToForm("Nombre:");
        capacityLbl = ComponentBuilder.BuildLabelToForm("Capacidad de personas:");
        durationLbl = ComponentBuilder.BuildLabelToForm("Duración:");
        priceLbl = ComponentBuilder.BuildLabelToForm("Precio:");

        idTxt = ComponentBuilder.BuildTextFieldToForm();
        idTxt.setEditable(false);
        nameTxt = ComponentBuilder.BuildTextFieldToForm();
        capacityTxt = ComponentBuilder.BuildTextFieldToForm();
        durationTxt = ComponentBuilder.BuildTextFieldToForm();
        priceTxt = ComponentBuilder.BuildTextFieldToForm();

        JPanel form = new JPanel(new GridLayout(10, 1));
        form.setBorder(new EmptyBorder(10, 60, 10, 60));

        form.add(idLbl);
        form.add(idTxt);
        form.add(nameLbl);
        form.add(nameTxt);
        form.add(capacityLbl);
        form.add(capacityTxt);
        form.add(durationLbl);
        form.add(durationTxt);
        form.add(priceLbl);
        form.add(priceTxt);

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

        JButton acceptButton = ComponentBuilder.BuildButton("actualizar", "update");
        acceptButton.addActionListener(event -> {
            try {
                TEscapeRoom tEscapeRoom = new TEscapeRoom();
                tEscapeRoom.setId(Validator.NumericFieldValidator(idTxt.getText(), "ID"));
                tEscapeRoom.setNombre(Validator.EmptyFieldValidator(nameTxt.getText(), "Nombre"));
                tEscapeRoom.setCapacidadPersonas(Validator.NumericFieldValidator(capacityTxt.getText(), "Capacidad"));
                tEscapeRoom.setDuracion(Validator.NumericFieldValidator(durationTxt.getText(), "Duración"));
                tEscapeRoom.setPrecio(Validator.DoubleFieldValidator(priceTxt.getText(), "Precio"));

                int response = ShowConfirmationMessage(CONFIRMATION_MESSAGE + tEscapeRoom);
                if (response == 0) {
                    Context context = new Context(tEscapeRoom, Event.UpdateEscapeRoom);
                    Controller.getInstance().action(context);
                }
            } catch (Exception e) {
                ShowErrorMessage(e.getMessage());
            }
        });

        toolbar.add(cancelButton);
        toolbar.add(acceptButton);
        toolbar.setLayout(new GridLayout(1, 2));
        return toolbar;
    }

    @Override
    public void update(Context context) {
        switch (context.getEvent()) {
            case UpdateEscapeRoomOK:
                ShowSuccessMessage(SUCCESS_MESSAGE + context.getData());
                break;
            case UpdateEscapeRoomError:
                ShowErrorMessage(context.getData().toString());
                break;
            case UpdateEscapeRoomView:
                TEscapeRoom escapeRoom = (TEscapeRoom) context.getData();
                this.idTxt.setText(String.valueOf(escapeRoom.getId()));
                this.nameTxt.setText(String.valueOf(escapeRoom.getNombre()));
                this.capacityTxt.setText(String.valueOf(escapeRoom.getCapacidadPersonas()));
                this.durationTxt.setText(String.valueOf(escapeRoom.getDuracion()));
                this.priceTxt.setText(String.valueOf(escapeRoom.getPrecio()));
                break;
        }
    }
}
