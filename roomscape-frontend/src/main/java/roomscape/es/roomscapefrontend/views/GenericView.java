package roomscape.es.roomscapefrontend.views;

import roomscape.es.roomscapefrontend.app_controller.Context;
import roomscape.es.roomscapefrontend.app_controller.Event;
import roomscape.es.roomscapefrontend.app_controller.controller.Controller;
import roomscape.es.roomscapefrontend.utils.Configuration;

import javax.swing.*;
import java.awt.*;

public abstract class GenericView extends JFrame {

    protected static final String EDIT_ICON = "edit";
    protected static final String RESERVATION_ICON = "reserva";
    protected static final String BAJA_ICON = "baja";

    protected static final String ALERT_TITLE = "¡Atención!";
    protected static final String SUCCESS_TITLE = "¡Operación Exitosa!";
    protected static final String ERROR_TITLE = "¡Error!";
    protected final static Font DEFAULT_FONT = new Font("sansserif", Font.BOLD, 20);
    protected final static Font DEFAULT_BUTTONS_FONT = new Font("Tahoma", 1, 11);

    private Configuration config = Controller.getInstance().getConfiguration();

    protected abstract void initComponents();

    protected abstract JPanel buildCentralPanel();

    protected void setDefaultConfig() {
        this.setSize(744, 454);
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(config.getIconScapeRoom()));
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setTitle("RoomScape");
    }

    protected void close() {
        this.dispose();
    }

    public JPanel BuildTitle(String title) {
        JPanel titlePanel = new JPanel();
        JLabel label = new JLabel(title);
        label.setFont(DEFAULT_FONT);
        titlePanel.add(label);
        return titlePanel;
    }

    protected JPanel buildLeftPanel(Event currentView) {

        JPanel menuPanel = new JPanel(new GridLayout(8, 1));

        JButton buttonScapeRooms = new JButton();
        buttonScapeRooms.setFont(DEFAULT_BUTTONS_FONT);
        buttonScapeRooms.setForeground(Color.BLACK);
        buttonScapeRooms.setText("Escape Rooms");
        if (currentView == Event.ListEscapeRoom) {
            buttonScapeRooms.setBackground(Color.BLACK);
            buttonScapeRooms.setForeground(Color.WHITE);
        }
        buttonScapeRooms.addActionListener(evt -> {
            Controller.getInstance().action(new Context(null, Event.AbrirEscapeRoomView));
            dispose();
        });

        JButton buttonReservation = new JButton();
        buttonReservation.setFont(DEFAULT_BUTTONS_FONT);
        buttonReservation.setForeground(Color.BLACK);
        buttonReservation.setText("Reservas");
        if (currentView == Event.UpdateEscapeRoomView) {
            buttonScapeRooms.setBackground(Color.BLACK);
            buttonScapeRooms.setForeground(Color.WHITE);
        }
        buttonReservation.addActionListener(evt -> {
            Controller.getInstance().action(new Context(null, Event.AbrirEscapeRoomView));
            dispose();
        });

        JButton buttonClose = new JButton();
        buttonClose.setFont(DEFAULT_BUTTONS_FONT);
        buttonClose.setForeground(Color.BLACK);
        buttonClose.setText("Salir");
        buttonClose.addActionListener(evt -> {
            this.dispose();
        });

        menuPanel.add(buttonScapeRooms);
        menuPanel.add(buttonReservation);
        menuPanel.add(new JLabel());
        menuPanel.add(new JLabel());
        menuPanel.add(new JLabel());
        menuPanel.add(new JLabel());
        menuPanel.add(new JLabel());
        menuPanel.add(buttonClose);

        return menuPanel;
    }

    public void ShowAlertMessage(String message) {
        JOptionPane.showMessageDialog(null, message, ALERT_TITLE, JOptionPane.WARNING_MESSAGE);
    }

    public void ShowSuccessMessage(String message) {
        JOptionPane.showMessageDialog(null, message, SUCCESS_TITLE, JOptionPane.INFORMATION_MESSAGE);
    }

    public void ShowErrorMessage(String message) {
        JOptionPane.showMessageDialog(null, message, ERROR_TITLE, JOptionPane.ERROR_MESSAGE);
    }

    public int ShowConfirmationMessage(String message) {
        return JOptionPane.showConfirmDialog(null, message, ALERT_TITLE, JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
    }
}
