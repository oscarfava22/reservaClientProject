package Model;

public class Plat {

    private String nom;
    private float price;
    private int unitats;
    private int tipus;
    private int unitatsSeleccionades; //Indica el nombre d'unitats selecionades per l'usuari a la carta.
    private boolean servit; //Indica si s'ha servit o no un plat, de cara a mostrar l'estat de la comanda.

    public Plat (String nom, float price, int unitats, int tipus) {

        this.nom = nom;
        this.price = price;
        this.unitats = unitats;
        this.tipus = tipus;
    }

    public Plat (String nom, float price, int unitats, int tipus, int unitatsSeleccionades) {

        this.nom = nom;
        this.price = price;
        this.unitats = unitats;
        this.tipus = tipus;
        this.unitatsSeleccionades = unitatsSeleccionades;
    }

    public int getTipus() {
        return tipus;
    }

    public String getNom() {
        return nom;
    }

    public float getPrice() {
        return price;
    }

    public int getUnitats() {
        return unitats;
    }

    public boolean isServit() {
        return servit;
    }

    public void setUnitats(int unitats) { this.unitats = unitats; }

    public void incrementaUnitatsSeleccionades () {
        unitatsSeleccionades++;
    }

    public void decrementaUnitatsSeleccionades () {
        unitatsSeleccionades--;
    }

    public int getUnitatsSeleccionades() {
        return unitatsSeleccionades;
    }

    public void setUnitatsSeleccionades (int unitatsSeleccionades) {
        this.unitatsSeleccionades = unitatsSeleccionades;
    }
}
