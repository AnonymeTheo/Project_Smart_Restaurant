package backend;

import java.util.HashMap;
import java.util.HashSet;

public class Gericht{
    public String name;
    public float Preis;
    public HashMap<String, Float> zutaten;

    public Gericht(String name, float preis, HashMap<String, Float> zutaten){
        this.name = name;
        Preis = preis;
        this.zutaten = zutaten;
    }
}
