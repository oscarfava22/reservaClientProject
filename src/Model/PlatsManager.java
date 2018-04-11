package Model;

import java.util.ArrayList;

public class PlatsManager {

    private ArrayList<Plat> plats;
    private ArrayList<Plat> entrants;
    private ArrayList<Plat> primers;
    private ArrayList<Plat> postres;
    private ArrayList<Plat> begudes;
    private ArrayList<Plat> cistella; //Plats triats que encara no s'han enviat al servidor.
    private ArrayList<Plat> estatComanda;

    public PlatsManager() {
        plats = new ArrayList<>();
        entrants = new ArrayList<>();
        primers = new ArrayList<>();
        postres = new ArrayList<>();
        begudes = new ArrayList<>();
        cistella = new ArrayList<>();
        estatComanda = new ArrayList<>();
    }

    public PlatsManager(ArrayList<Plat> plats) {
        this.plats = plats;
        cistella = new ArrayList<>();
        estatComanda = new ArrayList<>();
        extreureTipusPlat();
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

    public ArrayList<Plat> getEstatComanda() {
        return estatComanda;
    }

    /**
     * Afegir unitat a les unitats seleccionades per l'usuari del plat ièssim a la carta.
     * @param i
     */
    public void afegirUnitat(int i) {

        plats.get(i).incrementaUnitatsSeleccionades();
    }

    /**
     * Treure unitat a les unitats seleccionades per l'usuari del plat ièssim a la carta.
     * @param i
     */
    public void treureUnitat(int i) {

        plats.get(i).decrementaUnitatsSeleccionades();
    }

    /**
     * Afegir el plat ièssim de la llista de plats a la comanda, en tantes unitats com diu el paràmetre unitats.
     * Si ja existeix el plat a la comanda, s'afegeixen les unitats que diu el paràmetre unitats.
     * @param i
     * @param unitats
     */
    public void afegirPlatComanda(int i, int unitats) {
        if (cistella != null) {
            for (Plat p : cistella) {
                if (p.getNom().equals(plats.get(i).getNom())) {
                    p.setUnitatsSeleccionades(p.getUnitatsSeleccionades() + unitats);
                    return;
                }
            }
        }
        cistella.add(new Plat(plats.get(i).getNom(), plats.get(i).getPrice(), plats.get(i).getUnitats(), plats.get(i).getTipus(), plats.get(i).getUnitatsSeleccionades()));

    }

    public void buidarCistella () {
        cistella = new ArrayList<>();
        for (Plat p : plats) {
            p.setUnitatsSeleccionades(0);
        }
    }

    public void resetejarUnitats() {
        for (Plat p : plats) {
            p.setUnitatsSeleccionades(0);
        }
    }

    /**
     * Afegir a la comanda actual els plats que es demanen al Servidor.
     */
    public void afegirComanda() {

        boolean trobat;

        for (Plat p : cistella) {
            trobat = false;
            for (Plat pc : estatComanda){
                if (pc.getNom().equals(p.getNom())){
                    pc.setUnitatsSeleccionades(pc.getUnitatsSeleccionades() + p.getUnitatsSeleccionades());
                    trobat = true;
                    break;
                }
            }
            if (!trobat) {
                estatComanda.add(p);
            }
        }
    }

    public int getNumPlatsCistella() {
        return cistella.size();
    }

    public void afegirUnitatCistella(int i) {
        cistella.get(i).setUnitatsSeleccionades(cistella.get(i).getUnitatsSeleccionades() + 1);
    }

    public void treureUnitatCistella(int i) {
        cistella.get(i).setUnitatsSeleccionades(cistella.get(i).getUnitatsSeleccionades() - 1);
    }

    public void treureCistella(int i) {
        cistella.remove(i);
    }

    public float getPreuComanda() {
        float preu = 0.0f;
        for (Plat p : estatComanda) {
            preu += (p.getPrice() * p.getUnitatsSeleccionades());
        }
        return preu;
    }
}
