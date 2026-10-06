package backend;

public class Mitarbeiter {

    public int id;
    public String name;
    public boolean beschäftigt;
    public String rolle;

    public Mitarbeiter(
            int id,
            String name,
            boolean beschäftigt,
            String rolle) {

        this.id = id;
        this.name = name;
        this.beschäftigt = beschäftigt;
        this.rolle = rolle;
    }
}