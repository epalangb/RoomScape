package roomscape.es.roomscape.presentacion;

import javax.swing.*;

public class ErrorView extends JFrame{

    public ErrorView(String errorMsg){
        JOptionPane.showMessageDialog(null, errorMsg, "ERROR", JOptionPane.ERROR_MESSAGE);

    }
}
