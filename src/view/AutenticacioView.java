package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;

public class AutenticacioView extends JFrame {
    public static final String ACCESS = "ACCESS";
    private JTextField jtfName;
    private JTextField jtfPassword;
    private JButton jbAccess;

    public AutenticacioView(){

        setSize(350,350);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Autenticació");

        //NORTH
        final JPanel jpNorth = new JPanel();
        final JLabel jlTitle = new JLabel("NOM RESTAURANT");
        jpNorth.add(jlTitle);

        //CENTER
        final JPanel jcbCenter = new JPanel();
        jcbCenter.setLayout(new BoxLayout(jcbCenter, BoxLayout.Y_AXIS));
        jcbCenter.setBorder(BorderFactory.createMatteBorder(1,0,1,0,Color.BLACK));

        //name text
        jtfName = new JTextField();
        final JPanel jpLeftCenter = new JPanel();
        jpLeftCenter.setLayout(new GridLayout(1,2));
        jpLeftCenter.setBorder(new EmptyBorder(10,10,10,10));
        final JLabel jlNomUser = new JLabel("Introdueix nom d'usuari: ");
        jlNomUser.setAlignmentX(Component.LEFT_ALIGNMENT);
        jpLeftCenter.add(jlNomUser);
        jpLeftCenter.add(jtfName);


        //contrassenya text
        jtfPassword = new JTextField();
        final JPanel jpRightCenter = new JPanel();
        jpRightCenter.setLayout(new GridLayout(1,2));
        jpRightCenter.setBorder(new EmptyBorder(10,10,10,10));
        final JLabel jlContra = new JLabel("Contrassenya: ");
        jlContra.setAlignmentX(Component.LEFT_ALIGNMENT);
        jpRightCenter.add(jlContra);
        jpRightCenter.add(jtfPassword);

        //add to center layout
        jcbCenter.add(new Container());
        jcbCenter.add(new Container());
        jcbCenter.add(new Container());
        jcbCenter.add(jpLeftCenter);
        jcbCenter.add(jpRightCenter);
        jcbCenter.add(new Container());
        jcbCenter.add(new Container());
        jcbCenter.add(new Container());
        //SOUTH
        JPanel jpSouth = new JPanel();
        jpSouth.setLayout(new BorderLayout());
        jpSouth.setBorder(new EmptyBorder(10,25,10,25));
        jbAccess = new JButton("Accedir");
        jpSouth.add(jbAccess);

        //add all to the final panel
        getContentPane().add(jcbCenter, BorderLayout.CENTER);
        getContentPane().add(jpNorth, BorderLayout.PAGE_START);
        getContentPane().add(jpSouth, BorderLayout.PAGE_END);
        setVisible(true);
    }

    public void showError(String message){
        JOptionPane.showMessageDialog(null, message,"An error ocurred",JOptionPane.ERROR_MESSAGE );
    }

    public String getJtfName() {
        return jtfName.getText();
    }

    public String getJtfPassword() {
        return jtfPassword.getText();
    }

    public void registerControllers (ActionListener actionListener) {
        jbAccess.addActionListener(actionListener);
        jbAccess.setActionCommand(ACCESS);
    }
}
