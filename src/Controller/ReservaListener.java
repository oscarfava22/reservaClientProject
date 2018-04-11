package Controller;

import Model.Plat;
import Model.PlatsManager;
import Network.NetworkManager;
import view.CartaView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;

public class ReservaListener implements ActionListener{

    private PlatsManager model;
    private CartaView view;
    private NetworkManager networkManager;

    public ReservaListener (PlatsManager model, CartaView view) {
        this.model = model;
        this.view = view;
    }

    public void registerNetwork(NetworkManager networkManager){
        this.networkManager = networkManager;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        switch (e.getActionCommand()){

            case CartaView.PAGAR:
                System.out.println("PAGAR");
                //Enviar fi sessió a Servidor
                break;

            case CartaView.DEMANAR:
                System.out.println("DEMANAR");
                //Enviar Comanda a Servidor
                try {
                    networkManager.sendOrder(model.getCistella());
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                model.buidarCistella();
                view.actualitzarCistella(model.getCistella());
                break;

        }

        for (int i = 0; i < model.getNumPlats(); i++) {

            if (e.getActionCommand().equals("AFEGIR_UNITAT_" + i)) {
                model.afegirUnitat(i);
                view.actualitzarPlats(model.getEntrants(), model.getPrimers(), model.getBegudes(), model.getPostres());
                //Sumar unitat a la vista/model del plat
                System.out.println("AFEGIR UNITAT");
            } else if (e.getActionCommand().equals("TREURE_UNITAT_" + i)) {

                model.treureUnitat(i);
                view.actualitzarPlats(model.getEntrants(), model.getPrimers(), model.getBegudes(), model.getPostres());
                //Restar unitat a la vista/model del plat
                System.out.println("TREURE UNITAT");
            } else if (e.getActionCommand().equals("AFEGIR_" + i)) {

                //Afegir plat a la vista/model de la comanda
                model.afegirPlatComanda(i, model.getPlats().get(i).getUnitats());
                view.actualitzarCistella(model.getCistella());
                System.out.println("AFEGIR");
            }
        }

    }

    public void setViewVisible() {
        view.setVisible(true);
    }


    public void actualitzarPlats(List<Plat> entrants, List<Plat> primers, List<Plat> begudes, List<Plat> postres) {
        view.actualitzarPlats(entrants, primers, begudes, postres);
    }


    public void actualitzarEstatComanda(String nomPlat) {
        model.actualitzarEstatComanda(nomPlat);
        view.actualitzarEstatComanda();
    }
}
