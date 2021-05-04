package roomscape.es.roomscapefrontend.utils;

import roomscape.es.roomscapefrontend.app_controller.controller.Controller;

import javax.swing.*;
import java.awt.*;

public class ComponentBuilder {

    private static Configuration config = Controller.getInstance().getConfiguration();

    public static final String EDIT = "edit";

    private final static String IMAGE_EXT = ".png";


    public static JTextField BuildTextFieldToForm() {

        Dimension d = new Dimension(500, 30);

        JTextField jTextField = new JTextField();
        jTextField.setMaximumSize(d);
        jTextField.setSize(d);
        return jTextField;
    }

    public static JLabel BuildLabelToForm(String text) {

        Dimension d = new Dimension(500, 30);

        JLabel jLabel = new JLabel(text);
        jLabel.setMaximumSize(d);
        jLabel.setSize(d);

        return jLabel;
    }

    public static JButton BuildButton(String message, String iconName) {

        JButton button = new JButton();
        button.setToolTipText(message);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setIcon((new ImageIcon((config.getImageFolder() + iconName + IMAGE_EXT))));
        button.setSize(32, 32);
        return button;
    }
}
