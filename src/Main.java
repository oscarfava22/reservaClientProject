import Controller.AutenticacioListener;
import Controller.ReservaListener;
import Model.Plat;
import Model.PlatsManager;
import Network.NetworkManager;
import view.AutenticacioView;
import view.CartaView;
import view.ItemEstatComanda;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<Plat> plats = new ArrayList<>();
        plats.add(new Plat("Amanida", 50, 5, 0));
        plats.add(new Plat("Oli", 50, 5, 0));
        plats.add(new Plat("Sal", 50, 5, 0));


        PlatsManager model = new PlatsManager(plats);
        CartaView secondView = new CartaView();
        AutenticacioView firstView = new AutenticacioView();
        ReservaListener controller = new ReservaListener(model, secondView);
        AutenticacioListener controller2 = new AutenticacioListener(firstView, controller);
        secondView.registerControllers(controller);
        firstView.registerControllers(controller2);
        controller.registerController(controller2);

        try {
           NetworkManager networkManager = new NetworkManager(controller, model);
           controller2.registerNetwork(networkManager);
           controller.registerNetwork(networkManager);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

}
