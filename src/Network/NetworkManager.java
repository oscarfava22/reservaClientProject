package Network;

import Controller.AutenticacioListener;
import Model.JsonManager;
import Model.Plat;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class NetworkManager extends Thread {
    private String IP;
    private int PORT;
    private final ObjectOutputStream oos;
    private final DataInputStream dis;
    private boolean running;
    private boolean waiting;
    private boolean signCorrect;
    private AutenticacioListener autenticacioListener;

    public NetworkManager(AutenticacioListener autenticacioListener) throws IOException{ //to avoid initialized message
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
                }
                if(orders.getClass().equals(Plat.class)){
                    //que rebré?
                }
            }
        } catch (IOException e) {
            stopServerConnection();
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
