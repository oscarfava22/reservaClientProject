package Model;

import java.util.ArrayList;

public class ReservaManager {

    private Plat[] plats; //Tots els plats del restaurant.
    private ArrayList<Plat> cistella; //Plats triats que encara no s'han enviat al servidor.

    public ReservaManager () {

        //Omplir array de plats. Aquest ha de mesurar tant com plats hi hagi.
        plats = new Plat[22];
    }

    public int getNumPlats () {
        return plats.length;
    }

}
