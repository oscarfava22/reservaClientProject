import Controller.AutenticacioListener;
import Controller.ReservaListener;
import Model.ReservaManager;
import Network.NetworkManager;
import view.AutenticacioView;
import view.CartaView;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        CartaView secondView = new CartaView(5, 10, 4, 3, 5, 5);
        AutenticacioView firstView = new AutenticacioView();
        ReservaManager model = new ReservaManager();
        ReservaListener controller = new ReservaListener(model, secondView);
        AutenticacioListener controller2 = new AutenticacioListener(firstView, controller);
        secondView.registerControllers(controller);
        firstView.registerControllers(controller2);
        try {
           NetworkManager n = new NetworkManager(controller2);
           controller2.registerNetwork(n);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}
