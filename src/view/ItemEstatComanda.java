package view;

import javax.swing.*;
import java.awt.*;

public class ItemEstatComanda extends JPanel {

    public ItemEstatComanda(String nom, JLabel jlUnitats, JLabel jlServit) {

        setLayout(new BorderLayout());

        final JPanel item = new JPanel();

        item.setLayout(new GridLayout(1,3));
        item.setBorder(BorderFactory.createLineBorder(Color.black));

        //left
        final JPanel jpLeft = new JPanel();
        jpLeft.setAlignmentX(LEFT_ALIGNMENT);
        final JLabel plat = new JLabel("Plat servit: ");
        JLabel jlNom = new JLabel(nom);
        jpLeft.add(plat);
        jpLeft.add(jlNom);
        item.add(jpLeft);

        //center
        final JPanel jpCenter = new JPanel();
        final JLabel unitats = new JLabel("Unitats demanades: ");
        jpCenter.add(unitats);
        jpCenter.add(jlUnitats);
        item.add(jpCenter);

        //right
        final JPanel jpRight = new JPanel();
        jpRight.setAlignmentX(RIGHT_ALIGNMENT);
        final JLabel servits = new JLabel("Unitats servides: ");
        jpRight.add(servits);
        jpRight.add(jlServit);
        item.add(jpRight);

        this.add(item, BorderLayout.NORTH);
    }
}
