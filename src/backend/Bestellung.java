package backend;

import java.time.LocalDate;

public class Bestellung {

    public int id;
    public String kundenwuensche;
    public BestellungsZustand status;
    public int prioritaet;
    public int bewertung;
    public LocalDate datumZeit;
    public Tisch tisch;
    public Mitarbeiter mitarbeiter;

    public Bestellung(
            int id,
            String kundenwuensche,
            BestellungsZustand status,
            int prioritaet,
            int bewertung,
            LocalDate datumZeit,
            Tisch tisch,
            Mitarbeiter mitarbeiter) {

        this.id = id;
        this.kundenwuensche = kundenwuensche;
        this.status = status;
        this.prioritaet = prioritaet;
        this.bewertung = bewertung;
        this.datumZeit = datumZeit;
        this.tisch = tisch;
        this.mitarbeiter = mitarbeiter;
    }
}
