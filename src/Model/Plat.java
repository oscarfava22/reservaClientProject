package Model;

public class Plat {

    private String nom;
    private float price;
    private int unitats;
    private int tipus;

    public Plat (String nom, int price, int unitats, int tipus) {

        this.nom = nom;
        this.price = price;
        this.unitats = unitats;
        this.tipus = tipus;
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

    public void setUnitats(int unitats) { this.unitats = unitats; }
}
