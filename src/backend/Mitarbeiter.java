package backend;

public class Mitarbeiter {

    public int id;
    public String name;
    public boolean beschäftigt;
    public MitarbeiterRolle rolle;

    public Mitarbeiter(
            int id,
            String name,
            boolean beschäftigt,
            MitarbeiterRolle rolle) {

        this.id = id;
        this.name = name;
        this.beschäftigt = beschäftigt;
        this.rolle = rolle;
    }
}