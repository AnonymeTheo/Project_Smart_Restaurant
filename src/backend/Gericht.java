package backend;

import java.util.HashMap;

public class Gericht {

    public int id;
    public String bezeichnung;
    public int zubereitungsZeit;
    public int preis;
    public HashMap<Zutat,Float> zutaten;

    public Gericht(
            int id,
            String bezeichnung,
            int zubereitungsZeit,
            int preis,
            HashMap<Zutat,Float> zutaten) {

        this.id = id;
        this.bezeichnung = bezeichnung;
        this.zubereitungsZeit = zubereitungsZeit;
        this.preis = preis;
        this.zutaten = zutaten;
    }
}