package backend;

import java.time.LocalDate;

public class BestellungsZustand {

    public int id;
    public String beschreibung;
    public LocalDate letzteÄnderung;

    public BestellungsZustand(
            int id,
            String beschreibung,
            LocalDate letzteÄnderung) {

        this.id = id;
        this.beschreibung = beschreibung;
        this.letzteÄnderung = letzteÄnderung;
    }
}