package backend;

import java.time.LocalDateTime;

public class Bestellung {

    public int id;
    public Gericht gericht;
    public String kundenwuensche;
    public BestellungsZustand status;
    public int prioritaet;
    public Integer bewertung;
    public LocalDateTime datumZeit;
    public Tisch tisch;
    public Mitarbeiter mitarbeiter;

    public Bestellung(
            int id,
            Gericht gericht,
            String kundenwuensche,
            BestellungsZustand status,
            int prioritaet,
            Integer bewertung,
            LocalDateTime datumZeit,
            Tisch tisch,
            Mitarbeiter mitarbeiter) {

        this.id = id;
        this.gericht = gericht;
        this.kundenwuensche = kundenwuensche;
        this.status = status;
        this.prioritaet = prioritaet;
        this.bewertung = bewertung;
        this.datumZeit = datumZeit;
        this.tisch = tisch;
        this.mitarbeiter = mitarbeiter;
    }
}