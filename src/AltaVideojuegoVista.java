package src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AltaVideojuegoVista {

    private JFrame frame = new JFrame();

    public AltaVideojuegoVista() {
        frame.setTitle("Alta de Videojuegos");

        JPanel panelButtons = new JPanel();
        JButton buttonAlta = new JButton();

        panelButtons.setLayout(new GridLayout(2, 2, 10, 10));

        //ALTA
        buttonAlta.setText("Alta");
        buttonAlta.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                // frame.setVisible(false);
                JDialog alta = new JDialog();
                alta.getContentPane().setLayout(new BorderLayout());
                alta.setVisible(true);
                alta.setTitle("Dar de alta un Videojuego");
                alta.setLocation(600, 350);

                JPanel panelNombre = new JPanel();
                JLabel nombreLabel = new JLabel("Nombre: ");
                JTextField nombre = new JTextField();
                nombre.setPreferredSize(new Dimension(150, 30));
                panelNombre.add(nombreLabel);
                panelNombre.add(nombre);

                JPanel panelDesc = new JPanel();
                JLabel descLabel = new JLabel("Descripcion: ");
                JTextField desc = new JTextField();
                desc.setPreferredSize(new Dimension(150, 30));
                panelDesc.add(descLabel);
                panelDesc.add(desc);

                JPanel panelConsola = new JPanel();
                JLabel consolaLabel = new JLabel("Consola: ");
                JTextField consola = new JTextField();
                consola.setPreferredSize(new Dimension(150, 30));
                panelConsola.add(consolaLabel);
                panelConsola.add(consola);
            }
        });
    }
}
