package Controller;

import view.AutenticacioView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AutenticacioListener implements ActionListener{

    private AutenticacioView view;
    private ReservaListener controller;

    public AutenticacioListener (AutenticacioView view, ReservaListener controller) {

        this.view = view;
        this.controller = controller;

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        switch (e.getActionCommand()) {

            case AutenticacioView.ACCESS:

                //Comprovar que el password tingui 6 digits.
                //Comprovar que el nom d'usuari no estigui buit.
                //Enviar i rebre resposta del servidor

                //Si la resposta del servidor és satisfactoria:
                controller.setViewVisible();
                view.setVisible(false);
                break;
        }
    }
}
