package backend;

public class Gericht {

    public int id;
    public String bezeichnung;
    public int zubereitungsZeit;
    public int preis;

    public Gericht(
            int id,
            String bezeichnung,
            int zubereitungsZeit,
            int preis) {

        this.id = id;
        this.bezeichnung = bezeichnung;
        this.zubereitungsZeit = zubereitungsZeit;
        this.preis = preis;
    }
}