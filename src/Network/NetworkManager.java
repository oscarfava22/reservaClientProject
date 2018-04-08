package Network;

import Controller.AutenticacioListener;
import Model.JsonManager;
import Model.Plat;
import Model.PlatsManager;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class NetworkManager extends Thread {
    private String IP;
    private int PORT;
    private final ObjectOutputStream oos;
    private final DataInputStream dis;
    private boolean running;
    private boolean waiting;
    private boolean signCorrect;
    private AutenticacioListener autenticacioListener;
    private PlatsManager pm;

    public NetworkManager(AutenticacioListener autenticacioListener, PlatsManager pm) throws IOException{ //to avoid initialized message

            this.pm = pm;
            JsonManager jm = new JsonManager();
            this.IP = jm.getIp();
            this.PORT = jm.getPort();
            Socket socket = new Socket(IP, PORT);
            oos = new ObjectOutputStream(socket.getOutputStream());
            dis = new DataInputStream(socket.getInputStream());
            running = true;
            this.autenticacioListener = autenticacioListener;
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
                if(orders.getClass().equals(Boolean.class)){
                    signCorrect = (boolean) orders;
                    waiting = false;
                }else {
                    try{
                        ArrayList<Plat> plats = (ArrayList<Plat>) orders;
                        if(plats.get(1).getClass().equals(Plat.class)){
                            pm.setPlats(plats);
                            pm.extreureTipusPlat();
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
            //do nothing
        }
        return signCorrect;
    }

    public void sendOrder() throws IOException {
    }

    public void sendMessageSignIn(String login, String pass) throws IOException {
        oos.writeChars(login + "%%" + pass);
    }
}
