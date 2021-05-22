package roomscape.es.roomscapefrontend.views.escape_room;

import roomscape.es.roomscapefrontend.app_controller.Context;
import roomscape.es.roomscapefrontend.app_controller.Event;
import roomscape.es.roomscapefrontend.app_controller.controller.Controller;
import roomscape.es.roomscapefrontend.utils.Configuration;
import roomscape.es.roomscapefrontend.views.GenericView;
import roomscape.es.roomscapefrontend.views.View;

import javax.swing.*;
import java.awt.*;

public class EscapeRoomView extends GenericView implements View {

    /**
     * Creates new form EscapeRoomView
     */
    private JFrame last;

    private Configuration config = Controller.getInstance().getConfiguration();

    private static final String RESERVATION_CONFIRMATION_MSG = "La reserva se ha registrado correctamente: ";

    // Variables declaration - do not modify
    private javax.swing.JButton buttonScapeRooms;
    private javax.swing.JButton buttonClose;
    private javax.swing.JButton buttonReservas;
    private javax.swing.JButton buttonBack;
    private javax.swing.JButton buttonAltaScapeRoom;
    private javax.swing.JButton buttonListarScapeRoom;
    private javax.swing.JLabel labelScapeRoom;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;


    public EscapeRoomView() {
        initComponents();
        setSize(744, 454);
        Image icon = Toolkit.getDefaultToolkit().getImage(config.getImageFolder() + "IconoApp_1.png");
        setIconImage(icon);
        setResizable(false);
        setLocationRelativeTo(null);
        last = null;
        setVisible(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    @Override
    protected void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        buttonScapeRooms = new javax.swing.JButton();
        buttonReservas = new javax.swing.JButton();
        buttonClose = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        labelScapeRoom = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        buttonBack = new javax.swing.JButton();
        buttonAltaScapeRoom = new javax.swing.JButton();
        buttonListarScapeRoom = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new Color(0, 0, 0)));

        buttonScapeRooms.setBackground(new java.awt.Color(0, 0, 0));
        buttonScapeRooms.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        buttonScapeRooms.setForeground(new java.awt.Color(255, 255, 255));
        buttonScapeRooms.setText("> Escape Rooms");
        buttonScapeRooms.setBorder(null);
        buttonScapeRooms.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        buttonReservas.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        buttonReservas.setText("Reservas");
        buttonReservas.setBorder(null);
        buttonReservas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        buttonClose.setBackground(new java.awt.Color(0, 0, 0));
        buttonClose.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        buttonClose.setForeground(new java.awt.Color(255, 255, 255));
        buttonClose.setText("Cerrar");
        buttonClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        buttonListarScapeRoom.setText("Listar Escape Rooms");
        buttonListarScapeRoom.addActionListener(e -> {
            Context c = new Context(null, Event.ListEscapeRoom);
            Controller control = Controller.getInstance();
            control.action(c);
            dispose();
        });


        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(18, 18, 18)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addComponent(buttonReservas, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(buttonScapeRooms, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(51, 51, 51)
                                                .addComponent(buttonClose)))
                                .addContainerGap(19, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(buttonScapeRooms, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(buttonReservas, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 314, Short.MAX_VALUE)
                                .addComponent(buttonClose)
                                .addContainerGap())
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new Color(0, 0, 0)));

        labelScapeRoom.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        labelScapeRoom.setText("Escape Room");

        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new Color(153, 153, 153)));

        buttonBack.setBackground(new java.awt.Color(0, 0, 0));
        buttonBack.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        buttonBack.setForeground(new java.awt.Color(255, 255, 255));
        buttonBack.setText("Atrás");
        buttonBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addContainerGap(220, Short.MAX_VALUE)
                                .addComponent(buttonBack, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(206, 206, 206))
        );
        jPanel3Layout.setVerticalGroup(
                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(buttonBack)
                                .addContainerGap())
        );

        buttonAltaScapeRoom.setText("Dar de alta Escape Room con datos básicos");
        buttonAltaScapeRoom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });


        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(labelScapeRoom, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(buttonAltaScapeRoom)
                                        .addComponent(buttonListarScapeRoom))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(labelScapeRoom, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(buttonAltaScapeRoom)
                                .addGap(18, 18, 18)
                                .addComponent(buttonListarScapeRoom)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>

    @Override
    protected JPanel buildCentralPanel() {
        return null;
    }

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {
        this.dispose();
    }

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {
        Context c = new Context(null, Event.AbrirMainView);
        Controller control = Controller.getInstance();
        control.action(c);
        this.dispose();
    }

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {
        Context c = new Context(null, Event.AbrirAltaEscapeRoomBasicoView);
        Controller control = Controller.getInstance();
        control.action(c);
        this.dispose();
    }

    public JFrame getLast() {
        return last;
    }

    public void setLast(JFrame last) {
        this.last = last;
    }

    @Override
    public void update(Context context) {

        switch (context.getEvent()) {
            case AltaReservaOK:
                this.ShowSuccessMessage(RESERVATION_CONFIRMATION_MSG + context.getData().toString());
                break;
        }
    }
    // End of variables declaration

}
