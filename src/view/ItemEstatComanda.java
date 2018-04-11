package view;

import javax.swing.*;
import java.awt.*;

public class ItemEstatComanda extends JPanel {

    public ItemEstatComanda(String nom, JLabel jlUnitats, JLabel jlServit) {

        this.setLayout(new FlowLayout());
        this.setBorder(BorderFactory.createLineBorder(Color.black));

        JLabel jlNom = new JLabel(nom);
        jlNom.setBorder((BorderFactory.createEmptyBorder(0, 10, 0, 10)));

        this.add(jlNom);
        this.add(jlUnitats);
        this.add(jlServit);
    }
}
