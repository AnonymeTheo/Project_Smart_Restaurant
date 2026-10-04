package backend;

public class Tisch {

    public int id;
    public int kapazität;
    public boolean belegt;

    public Tisch(
            int id,
            int kapazität,
            boolean belegt) {

        this.id = id;
        this.kapazität = kapazität;
        this.belegt = belegt;
    }
}