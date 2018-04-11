package Network;

import Controller.AutenticacioListener;
import Controller.ReservaListener;
import Model.JsonManager;
import Model.Plat;
import Model.PlatsManager;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class NetworkManager extends Thread {
    private String IP;
    private int PORT;
    private final ObjectOutputStream oos;
    private final DataInputStream dis;
    private boolean running;
    private boolean waiting;
    private boolean signCorrect;
    private ReservaListener reservaListener;
    private PlatsManager model;

    public NetworkManager(ReservaListener reservaListener, PlatsManager model) throws IOException{ //to avoid initialized message

            this.model = model;
            JsonManager jm = new JsonManager();
            this.IP = jm.getIp();
            this.PORT = jm.getPort();
            Socket socket = new Socket(IP, PORT);
            oos = new ObjectOutputStream(socket.getOutputStream());
            dis = new DataInputStream(socket.getInputStream());
            running = true;
            this.reservaListener = reservaListener;
            waiting = false;
            start();
    }


    private void stopServerConnection () {
        running = false;
        interrupt();
    }


    @Override
    public void run () {
        try {
            while (running) {
                Object orders = dis.readUTF();
                if (orders.getClass().equals(Boolean.class)) {
                    signCorrect = (boolean) orders;
                    waiting = false;
                } else if (orders.getClass().equals(String.class)) { //Rebem String amb el nom del plat que ja s'ha servit

                    reservaListener.actualitzarEstatComanda((String) orders);
                } else {
                    try{
                        ArrayList<Plat> plats = (ArrayList<Plat>) orders;
                        if(plats.get(1).getClass().equals(Plat.class)){
                            model.setPlats(plats);
                            model.extreureTipusPlat();
                            reservaListener.actualitzarPlats((ArrayList<Plat>)orders, (ArrayList<Plat>)orders, (ArrayList<Plat>)orders, (ArrayList<Plat>)orders);
                        }
                    }catch (ClassCastException e){
                        System.err.println("Wrong Message From Server");
                    }
                }

            }
        } catch (IOException e) {
            stopServerConnection();
            System.err.println(e.getMessage());
        }
    }


    public boolean singInIsCorrect(){
        waiting = true;
        while (waiting){
            //do nothing. Està treballant el run.
        }
        return signCorrect;
    }

    public void sendOrder(List<Plat> order) throws IOException {

        oos.writeObject(order);
    }

    public void sendMessageSignIn(String login, String pass) throws IOException {
        oos.writeChars(login + "%%" + pass);
    }
}
