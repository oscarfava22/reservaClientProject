package Network;

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

    /**
     * Paramos el proceso de conexion con el servidor
     */
    private void stopServerConnection () {
        running = false;
        interrupt();
    }

    /**
     * Procedimiento que estara activo mientras no se cierre la conexion.
     */
    @Override
    public void run () {
        try {
            while (running) {
                Object orders = dis.readUTF();
                if (orders.getClass().equals(Boolean.class)) {
                    signCorrect = (boolean) orders;
                    waiting = false;
                } else if (orders.getClass().equals(String.class)) {

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

    /**
     * Proceso complementario para la comprobacion de que el usuario es correcto
     * @return si es correcto o no
     */
    public boolean singInIsCorrect(){
        waiting = true;
        while (waiting){
        }
        return signCorrect;
    }

    /**
     *
     * @param order
     * @throws IOException
     */
    public void sendOrder(List<Plat> order) throws IOException {

        oos.writeObject(order);
    }

    /**
     *
     * @param login
     * @param pass
     * @throws IOException
     */
    public void sendMessageSignIn(String login, String pass) throws IOException {
        oos.writeChars(login + "%%" + pass);
    }

    /**
     *
     * @throws IOException
     */
    public void sendPagar () throws IOException {
        oos.writeChars("PAGAR");
    }
}
