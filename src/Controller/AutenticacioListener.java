package Controller;

import Network.NetworkManager;
import view.AutenticacioView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class AutenticacioListener implements ActionListener{

    private AutenticacioView viewAutenticacio;
    private ReservaListener controller;
    private NetworkManager networkManager;

    public AutenticacioListener (AutenticacioView viewAutenticacio, ReservaListener controller) {

        this.viewAutenticacio = viewAutenticacio;
        this.controller = controller;
    }

    public void registerNetwork(NetworkManager n){
        this.networkManager = n;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        switch (e.getActionCommand()) {

            case AutenticacioView.ACCESS:

                //Comprovar que el nom d'usuari no estigui buit.
                if(viewAutenticacio.getJtfName().equals("") || viewAutenticacio.getJtfPassword().equals("") ){
                    viewAutenticacio.showError("Has deixat el nom o la contrassenya en blanc." + System.lineSeparator() +
                            "Siusplau, escriu un nom i una contrassenya.");
                    break;

                }else {//Enviar i rebre resposta del servidor

                    try {
                        networkManager.sendMessageSignIn(viewAutenticacio.getJtfName(), viewAutenticacio.getJtfPassword());
                    } catch (IOException e1) {
                        System.err.println(e1.getMessage());
                        break;
                    }
                    //si la resposta no es satisfactoria
                    if(!networkManager.singInIsCorrect()){
                        viewAutenticacio.showError("Sign-in incorrect");
                    }else {
                        //Si la resposta del servidor és satisfactoria:
                        controller.setViewVisible();
                        viewAutenticacio.setVisible(false);
                    }


                }



                break;
        }
    }
}
