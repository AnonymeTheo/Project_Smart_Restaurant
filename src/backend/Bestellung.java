package backend;

public class Bestellung{
    public Gericht gericht;
    public Mitarbeiter koch;
    public BestellungsZustand status;
    public int bewertung;
    public Tisch tisch;
    public String sonderWünsche;

    public Bestellung(Gericht gericht, Mitarbeiter koch, BestellungsZustand status, int bewertung, Tisch tisch, String sonderWünsche){
        this.gericht = gericht;
        this.koch = koch;
        this.status = status;
        this.bewertung = bewertung;
        this.tisch = tisch;
        this.sonderWünsche = sonderWünsche;
    }
}
