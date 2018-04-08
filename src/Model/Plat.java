package Model;

public class Plat {

    private String nom;
    private float price;
    private int unitats;
    private int tipus;

    public Plat (String nom, int price, int unitats) {

        this.nom = nom;
        this.price = price;
        this.unitats = unitats;
    }

    public int getTipus() {
        return tipus;
    }
}
