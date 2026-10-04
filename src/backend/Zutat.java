package backend;

public class Zutat {

    public int id;
    public String bezeichnung;
    public int bestand;
    public String allergien;

    public Zutat(
            int id,
            String bezeichnung,
            int bestand,
            String allergien) {

        this.id = id;
        this.bezeichnung = bezeichnung;
        this.bestand = bestand;
        this.allergien = allergien;
    }
}