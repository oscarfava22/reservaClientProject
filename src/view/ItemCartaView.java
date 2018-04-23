package view;

import javax.swing.*;
import java.awt.*;

public class ItemCartaView extends JPanel {

    public ItemCartaView(String nom, JButton jbAfegirUnitats, JButton jbTreureUnitats, JButton jbAfegir, JLabel jlUnitats) {

        this.setLayout(new GridLayout(1, 5));
        this.setBorder(BorderFactory.createLineBorder(Color.black));

        JLabel jlNom = new JLabel(nom);
        jlNom.setBorder((BorderFactory.createEmptyBorder(0, 10, 0, 10)));

        JPanel jpAfegirUnitats = new JPanel();
        jpAfegirUnitats.setLayout(new BoxLayout(jpAfegirUnitats, BoxLayout.Y_AXIS));
        jpAfegirUnitats.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        jpAfegirUnitats.add(Box.createVerticalGlue());
        jbAfegirUnitats.setAlignmentX(Component.CENTER_ALIGNMENT);
        jbAfegirUnitats.setAlignmentY(Component.CENTER_ALIGNMENT);
        jbAfegirUnitats.setMinimumSize(new Dimension(45, 25));
        jbAfegirUnitats.setMaximumSize(new Dimension(45, 25));
        jpAfegirUnitats.add(jbAfegirUnitats);

        jpAfegirUnitats.add(Box.createVerticalGlue());
        jbTreureUnitats.setAlignmentX(Component.CENTER_ALIGNMENT);
        jbTreureUnitats.setAlignmentY(Component.CENTER_ALIGNMENT);
        jbTreureUnitats.setMinimumSize(new Dimension(45, 25));
        jbTreureUnitats.setMaximumSize(new Dimension(45, 25));
        jpAfegirUnitats.add(jbTreureUnitats);
        jpAfegirUnitats.add(Box.createVerticalGlue());

        JPanel jpAfegir = new JPanel();
        jpAfegir.setLayout(new BoxLayout(jpAfegir, BoxLayout.Y_AXIS));

        jpAfegir.add(Box.createVerticalGlue());
        jpAfegir.add(jbAfegir);
        jpAfegir.add(Box.createVerticalGlue());

        this.add(jlNom);
        this.add(jlUnitats);
        this.add(jpAfegirUnitats);
        this.add(jpAfegir);
    }
}
