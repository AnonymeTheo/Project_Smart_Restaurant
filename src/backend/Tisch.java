package backend;

public class Tisch {

    public int nummer;
    public int kapazität;
    public TischStatus belegt;

    public Tisch(
            int nummer,
            int kapazität,
            TischStatus belegt) {

        this.nummer = nummer;
        this.kapazität = kapazität;
        this.belegt = belegt;
    }
}