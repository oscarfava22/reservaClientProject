package Controller;

import Model.Plat;
import Network.NetworkManager;
import view.AutenticacioView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

public class AutenticacioListener implements ActionListener{

    private AutenticacioView viewAutenticacio;
    private ReservaListener controller;
    private NetworkManager networkManager;

    public AutenticacioListener (AutenticacioView viewAutenticacio, ReservaListener controller) {

        this.viewAutenticacio = viewAutenticacio;
        this.controller = controller;
    }

    public void showError(String error){
        viewAutenticacio.showError(error);
    }

    public void registerNetwork(NetworkManager n){
        this.networkManager = n;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        switch (e.getActionCommand()) {

            case AutenticacioView.ACCESS:
                if(1 == 1){ //TODO
                    //Esperar a rebre la carta de plats.

                    ArrayList<Plat> entrants = new ArrayList<>(); //Això ho farà NETWORK MANAGER
                    ArrayList<Plat> primers = new ArrayList<>(); //Això ho farà NETWORK MANAGER
                    ArrayList<Plat> begudes = new ArrayList<>(); //Això ho farà NETWORK MANAGER
                    ArrayList<Plat> postres = new ArrayList<>(); //Això ho farà NETWORK MANAGER

                    entrants.add(new Plat("Amanida", 50, 5, 0)); //Això ho farà NETWORK MANAGER
                    entrants.add(new Plat("Oli", 50, 5, 0)); //Això ho farà NETWORK MANAGER
                    entrants.add(new Plat("Sal", 50, 5, 0)); //Això ho farà NETWORK MANAGER

                    controller.actualitzarPlats(entrants, primers, begudes, postres); //Això ho farà NETWORK MANAGER

                    controller.setViewVisible(); //Un cop tenim la llista de plats actualitzada a model i vista.
                    viewAutenticacio.setVisible(false);
                    System.out.println("Modificacio adaptada a testing");
                    break;
                }

                //Comprovar que el nom d'usuari no estigui buit.
                if(viewAutenticacio.getJtfName().equals("") || viewAutenticacio.getJtfPassword().equals("") ){
                    viewAutenticacio.showError("Has deixat el nom o la contrassenya en blanc." + System.lineSeparator() +
                            "Siusplau, escriu un nom i una contrassenya.");
                    break;

                }else {//Enviar i rebre resposta del servidor

                    try {
                        networkManager.sendMessageSignIn(viewAutenticacio.getJtfName(), viewAutenticacio.getJtfPassword());

                    } catch (IOException e1) {
                        showError(e1.getMessage());
                        break;

                    }catch (NullPointerException e2){
                        String message = e2.getMessage();
                        if(e2.getMessage() == null){
                            message = "No s'ha conectat el servidor correctament";
                        }
                        showError(message);
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

    public void setViewVisible(boolean viewVisible) {
        viewAutenticacio.setVisible(viewVisible);
    }
}
