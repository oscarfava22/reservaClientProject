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

    /**
     * Constructor sin parametros, inicializa todos los arrays
     */
    public PlatsManager() {
        plats = new ArrayList<>();
        entrants = new ArrayList<>();
        primers = new ArrayList<>();
        postres = new ArrayList<>();
        begudes = new ArrayList<>();
        cistella = new ArrayList<>();
        estatComanda = new ArrayList<>();

    }

    /**
     * Constructor con parametro d'array list de platos.
     * @param plats platos disponibles en el almacen
     */
    public PlatsManager(ArrayList<Plat> plats) {
        this.plats = plats;
        cistella = new ArrayList<>();
        estatComanda = new ArrayList<>();
        extreureTipusPlat();
    }

    /**
     * Establecer el array de platos
     * @param plats array de platos en el almacen
     */
    public void setPlats(ArrayList<Plat> plats) {
        this.plats = plats;
    }

    /**
     * Transforma el array de platos en 4 arrays segun el atributo de Plat.tipus
     */
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

    /**
     * Obtiene la cantidad de platos disponibles
     * @return numero de platos disponibles
     */
    public int getNumPlats () {
        return plats.size();
    }

    /**
     *
     * @param nomPlat
     */
    public void actualitzarEstatComanda(String nomPlat) {
    }

    /**
     * Devuelve el array de platos
     * @return array de platos
     */
    public ArrayList<Plat> getPlats() {
        return plats;
    }

    /**
     * Devuelve el array de entrantes, subarray de platos
     * @return array de entrantes
     */
    public ArrayList<Plat> getEntrants() {
        return entrants;
    }

    /**
     * Devuelve el array de primeros, subarray de platos
     * @return array de primeros
     */
    public ArrayList<Plat> getPrimers() {
        return primers;
    }

    /**
     * Devuelve el array de postres, subarray de platos
     * @return array de postres
     */
    public ArrayList<Plat> getPostres() {
        return postres;
    }

    /**
     * Devuelve el array de bebidas, subarray de platos
     * @return array de bebidas
     */
    public ArrayList<Plat> getBegudes() {
        return begudes;
    }

    /**
     * Devuelve los platos comprados por el usuario
     * @return array de platos comprados
     */
    public ArrayList<Plat> getCistella() {
        return cistella;
    }

    /**
     * Devuelve los platos que se han pedido al restaurante
     * @return platos pedidos como comanda
     */
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

    /**
     * Vacia la cesta de las unidades compradas
     */
    public void buidarCistella () {
        cistella = new ArrayList<>();
        for (Plat p : plats) {
            p.setUnitatsSeleccionades(0);
        }
    }

    /**
     * Vacia las unidades seleccionadas de cada plato
     */
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

    /**
     * Devuelve el total de tipo de plato diferente que hay pedido, no cuanta cantidad de platos hay en total
     * @return cantidad de platos diferentes
     */
    public int getNumPlatsCistella() {
        return cistella.size();
    }

    /**
     * Añade una unidad de un tipo de plato concreto, es decir, la cantidad de un unico de tipo de plato que se vera aumentada
     * @param i que plato se esta aumentado en cantidad
     */
    public void afegirUnitatCistella(int i) {
        cistella.get(i).setUnitatsSeleccionades(cistella.get(i).getUnitatsSeleccionades() + 1);
    }

    /**
     * Restar en unidad de un tipo de plato concreto, es decir, la cantidad de un unico de tipo de plato que se vera aumentada
     * @param i que plato se esta reduciendo en cantidad
     */
    public void treureUnitatCistella(int i) {
        cistella.get(i).setUnitatsSeleccionades(cistella.get(i).getUnitatsSeleccionades() - 1);
    }

    /**
     * Elimina el tipo de plato y consecuentemente todas las unidades que este contenia
     * @param i que plato se esta eliminando
     */
    public void treureCistella(int i) {
        cistella.remove(i);
    }

    /**
     * Obtiene el precio de la comanda que se ha realizado
     * @return precio total de la comanda 
     */
    public float getPreuComanda() {
        float preu = 0.0f;
        for (Plat p : estatComanda) {
            preu += (p.getPrice() * p.getUnitatsSeleccionades());
        }
        return preu;
    }
}
