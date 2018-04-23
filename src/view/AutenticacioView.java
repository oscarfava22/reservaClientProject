package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class AutenticacioView extends JFrame {
    public static final String ACCESS = "ACCESS";
    private JTextField jtfName;
    private JPasswordField jtfPassword;
    private JButton jbAccess;

    public AutenticacioView(){

        setSize(450,400);
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
        final JPanel jpLeftCenter = new JPanel();
        jtfName = new JTextField(20);
        jpLeftCenter.setLayout(new FlowLayout(FlowLayout.LEFT));
        final JLabel jlNomUser = new JLabel("Introdueix nom d'usuari: ");
        jlNomUser.setLabelFor(jtfName);
        jpLeftCenter.setBorder(BorderFactory.createEtchedBorder(1));

        jpLeftCenter.add(jlNomUser);
        jpLeftCenter.add(jtfName);



        final JPanel jpRightCenter = new JPanel();
        jpRightCenter.setLayout(new FlowLayout(FlowLayout.LEFT));
        jpRightCenter.setBorder(BorderFactory.createEtchedBorder(1));
        final JLabel jlContra = new JLabel("Contrassenya: " +
                "                 ");
        jtfPassword = new JPasswordField(20);
        jtfPassword.setName("Escriu aqui la teva contrassenya");
        jlContra.setLabelFor(jtfPassword);

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

    /**
     * Mostrar error por un dialogo
     * @param message mensaje que muestra en el dialogo
     */
    public void showError(String message){
        JOptionPane.showMessageDialog(null, message,"An error ocurred",JOptionPane.ERROR_MESSAGE );
    }

    public String getJtfName() {
        return jtfName.getText();
    }

    public String getJtfPassword() {
        return jtfPassword.getText();
    }

    /**
     * Per atribuir un listener als components que interactuen amb l'usuari
     * @param actionListener listener que estara escoltant
     */
    public void registerControllers (ActionListener actionListener) {
        jbAccess.addActionListener(actionListener);
        jbAccess.setActionCommand(ACCESS);
    }
}
