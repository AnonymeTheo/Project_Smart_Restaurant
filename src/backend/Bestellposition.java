package backend;

public class Bestellposition {

    public int id;
    public Bestellung bestellung;
    public Gericht gericht;
    public int menge;

    public Bestellposition(
            int id,
            Bestellung bestellung,
            Gericht gericht,
            int menge) {

        this.id = id;
        this.bestellung = bestellung;
        this.gericht = gericht;
        this.menge = menge;
    }
}