package backend;

public class GerichtZutatenposition {

    public int id;
    public Zutat zutat;
    public Gericht gericht;
    public int menge;

    public GerichtZutatenposition(
            int id,
            Zutat zutat,
            Gericht gericht,
            int menge) {

        this.id = id;
        this.zutat = zutat;
        this.gericht = gericht;
        this.menge = menge;
    }
}