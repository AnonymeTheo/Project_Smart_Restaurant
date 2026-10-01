package backend;

public class Zutat{
    public String bezeichnung;
    public float menge;
    public char[] allergene;

    public Zutat(String bezeichnung, float menge, char[] allergene){
        this.bezeichnung = bezeichnung;
        this.menge = menge;
        this.allergene = allergene;
    }
}
