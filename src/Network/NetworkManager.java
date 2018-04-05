package Network;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class NetworkManager extends Thread {

    private static final String IP = "127.0.0.1"; //Obtenir de config.json
    private static final int PORT = 54321; //Obtenir de config.json
    private final ObjectOutputStream oos;
    private final DataInputStream dis;

    public NetworkManager () throws IOException {

        Socket socket = new Socket(IP, PORT);
        oos = new ObjectOutputStream(socket.getOutputStream());
        dis = new DataInputStream(socket.getInputStream());

    }

    //Enviar el que ha introduit l'usuari a autenticació
    //Rebre resposta del servidor dient si és correcte o no

    //Enviar comanda
    //Rebre resposta del servidor dient si és correcte o no

    //Enviar missatge "Cancel·lar comanda" al servidor si l'usuari decideix pagar i marxar.

    //Rebre informació de si ja s'ha servit un plat

    //Rebre tots els plats que ofereix el restaurant
}
