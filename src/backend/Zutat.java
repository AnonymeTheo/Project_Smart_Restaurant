package backend;

public class Zutat {

    public int id;
    public String bezeichnung;
    public int bestand;
    public Allergie allergie;

    public Zutat(
            int id,
            String bezeichnung,
            int bestand,
            Allergie allergie) {

        this.id = id;
        this.bezeichnung = bezeichnung;
        this.bestand = bestand;
        this.allergie = allergie;
    }
}