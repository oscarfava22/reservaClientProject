package Controller;

import Model.Plat;
import Model.PlatsManager;
import Network.NetworkManager;
import view.CartaView;
import view.MessageView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;

public class ReservaListener implements ActionListener{

    private PlatsManager model;
    private CartaView view;
    private NetworkManager networkManager;
    private AutenticacioListener controller;

    public ReservaListener (PlatsManager model, CartaView view) {
        this.model = model;
        this.view = view;
    }

    /**
     * Enregistrar gestor de xarxa, per establir connexions amb el servidor.
     * @param networkManager: gestor de xarxa.
     */
    public void registerNetwork(NetworkManager networkManager){
        this.networkManager = networkManager;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        switch (e.getActionCommand()){

            case CartaView.PAGAR:

               try {
                    networkManager.sendPagar();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                //Enviar fi sessió a Servidor
                new MessageView(model.getPreuComanda());
                view.setVisible(false);
                controller.setViewVisible(true);

                break;

            case CartaView.DEMANAR:

                System.out.println("DEMANAR");
                //Enviar Comanda a Servidor
              /*  try {
                    networkManager.sendOrder(model.getCistella());
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                */model.afegirComanda();
                view.actualitzarEstatComanda(model.getEstatComanda());
                model.buidarCistella();
                view.actualitzarCistella(model.getCistella());

                //Actualitzar preu
                view.setPrice(model.getPreuComanda());
                break;

        }

        for (int i = 0; i < model.getNumPlats(); i++) {

            if (e.getActionCommand().equals("AFEGIR_UNITAT_" + i)) {
                model.afegirUnitat(i);
                view.actualitzarUnitats(i, model.getPlats().get(i).getUnitatsSeleccionades(), model.getPlats().get(i).getUnitats());
                //Sumar unitat a la vista/model del plat
                System.out.println("AFEGIR UNITAT" + i);

            } else if (e.getActionCommand().equals("TREURE_UNITAT_" + i)) {

                model.treureUnitat(i);
                view.actualitzarUnitats(i, model.getPlats().get(i).getUnitatsSeleccionades(), model.getPlats().get(i).getUnitats());
                //Restar unitat a la vista/model del plat
                System.out.println("TREURE UNITAT");

            } else if (e.getActionCommand().equals("AFEGIR_" + i)) {

                //Afegir plat a la vista/model de la comanda
                if (model.getPlats().get(i).getUnitatsSeleccionades() > 0) {
                    model.afegirPlatComanda(i, model.getPlats().get(i).getUnitatsSeleccionades());
                    view.actualitzarCistella(model.getCistella());
                    view.registerControllersCistella(this);
                }
                model.resetejarUnitats();
                view.resetejarUnitats();
                System.out.println("AFEGIR" + i);
            }
        }

        for (int i = 0; i < model.getNumPlatsCistella(); i++) {

            if (e.getActionCommand().equals("AFEGIR_UNITAT_CISTELLA_" + i)){
                model.afegirUnitatCistella(i);
                view.actualitzarUnitatsCistella(i, model.getCistella().get(i).getUnitatsSeleccionades());
                System.out.println("AFEGIR_UNITAT_CISTELLA");

            } else if (e.getActionCommand().equals("TREURE_UNITAT_CISTELLA_" + i)) {
                model.treureUnitatCistella(i);
                view.actualitzarUnitatsCistella(i, model.getCistella().get(i).getUnitatsSeleccionades());
                System.out.println("TREURE_UNITAT_CISTELLA");

                if (model.getCistella().get(i).getUnitatsSeleccionades() == 0) {
                    model.treureCistella(i);
                    view.actualitzarCistella(model.getCistella());
                    view.registerControllersCistella(this);
                }

            } else if (e.getActionCommand().equals("TREURE_CISTELLA_" + i)) {
                model.treureCistella(i);
                view.actualitzarCistella(model.getCistella());
                view.registerControllersCistella(this);

            }
        }

    }

    public void setViewVisible() {
        view.setVisible(true);
    }


    public void actualitzarPlats(List<Plat> entrants, List<Plat> primers, List<Plat> begudes, List<Plat> postres) {
        view.actualitzarPlats(entrants, primers, begudes, postres);
        view.registerControllers(this);
    }


    /**
     * Modificar l'estat del plat que té com a nom el que s'ha rebut com a paràmentre, posant el plat com a servit a l'estat de la comanda.
     * @param nomPlat: nom del plat que s'acaba de servir.
     */
    public void actualitzarEstatComanda(String nomPlat) {
        model.actualitzarEstatComanda(nomPlat);
        view.actualitzarEstatComanda(model.getEstatComanda());
    }

    /**
     * Enregistrar el controlador de la vista d'autenticació.
     * @param controller: controlador de la vista d'autenticació.
     */
    public void registerController(AutenticacioListener controller) {
        this.controller = controller;
    }
}
