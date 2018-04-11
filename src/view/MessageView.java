package view;

import javax.swing.*;


public class MessageView extends JOptionPane {

    public MessageView(float preu) {

        showMessageDialog(null, "Preu: " + preu + "€", "Pagar", JOptionPane.INFORMATION_MESSAGE);

        setVisible(true);
    }

}
