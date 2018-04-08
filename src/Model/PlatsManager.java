package Model;

import java.util.ArrayList;

public class PlatsManager {
    ArrayList<Plat> plats;
    ArrayList<Plat> entrants;
    ArrayList<Plat> primers;
    ArrayList<Plat> postres;
    ArrayList<Plat> begudes;

    public PlatsManager() {
    }

    private PlatsManager(ArrayList<Plat> plats, ArrayList<Plat> entrants, ArrayList<Plat> primers, ArrayList<Plat> postres, ArrayList<Plat> begudes) {
        this.plats = plats;
        this.entrants = entrants;
        this.primers = primers;
        this.postres = postres;
        this.begudes = begudes;
    }

    public void setPlats(ArrayList<Plat> plats) {
        this.plats = plats;
    }

    public void extreureTipusPlat(){

        for (Plat p : plats
             ) {
            switch (p.getTipus()){
                case 0:
                    entrants.add(p);
                    break;
                case 1:
                    primers.add(p);
                    break;
                case 2:
                    postres.add(p);
                    break;
                case 3:
                    begudes.add(p);
                    break;
            }
        }
    }
}
