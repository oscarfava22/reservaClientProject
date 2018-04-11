package Model;

import java.util.ArrayList;

public class PlatsManager {

    private ArrayList<Plat> plats;
    private ArrayList<Plat> entrants;
    private ArrayList<Plat> primers;
    private ArrayList<Plat> postres;
    private ArrayList<Plat> begudes;
    private ArrayList<Plat> cistella; //Plats triats que encara no s'han enviat al servidor.

    public PlatsManager() {
        plats = new ArrayList<>();
        entrants = new ArrayList<>();
        primers = new ArrayList<>();
        postres = new ArrayList<>();
        begudes = new ArrayList<>();
        cistella = new ArrayList<>();
    }

    public PlatsManager(ArrayList<Plat> plats, ArrayList<Plat> entrants, ArrayList<Plat> primers, ArrayList<Plat> postres, ArrayList<Plat> begudes) {
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

        entrants = new ArrayList<>();
        primers = new ArrayList<>();
        postres = new ArrayList<>();
        begudes = new ArrayList<>();

        for (Plat p : plats) {
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

    public int getNumPlats () {
        return plats.size();
    }

    public void actualitzarEstatComanda(String nomPlat) {
    }

    public ArrayList<Plat> getPlats() {
        return plats;
    }

    public ArrayList<Plat> getEntrants() {
        return entrants;
    }

    public ArrayList<Plat> getPrimers() {
        return primers;
    }

    public ArrayList<Plat> getPostres() {
        return postres;
    }

    public ArrayList<Plat> getBegudes() {
        return begudes;
    }

    public ArrayList<Plat> getCistella() {
        return cistella;
    }

    public void afegirUnitat(int i) {

        plats.get(i).setUnitats(plats.get(i).getUnitats() + 1);
        extreureTipusPlat();
    }

    public void treureUnitat(int i) {

        switch (plats.get(i).getTipus()){

            case 0:
                entrants.get(i).setUnitats(entrants.get(i).getUnitats() - 1);
                break;
            case 1:
                primers.get(i).setUnitats(primers.get(i).getUnitats() - 1);
                break;
            case 2:
                postres.get(i).setUnitats(postres.get(i).getUnitats() - 1);
                break;
            case 3:
                begudes.get(i).setUnitats(begudes.get(i).getUnitats() - 1);
                break;
        }
    }

    /**
     * Afegir el plat ièssim de la llista de plats a la comanda, en tantes unitats com diu el paràmetre unitats.
     * Si ja existeix el plat a la comanda, s'afegeixen les unitats que diu el paràmetre unitats.
     * @param i
     * @param unitats
     */
    public void afegirPlatComanda(int i, int unitats) {
        for (Plat p : cistella) {
            if(p.getNom().equals(plats.get(i).getNom())){
                p.setUnitats(p.getUnitats() + unitats);
                return;
            }
        }
        cistella.add(plats.get(i));

    }

    public void buidarCistella () {
        cistella = new ArrayList<>();
    }
}
