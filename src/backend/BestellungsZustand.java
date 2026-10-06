

package backend;

import java.time.LocalDateTime;

public class BestellungsZustand {

    public int id;
    public String beschreibung;
    public LocalDateTime letzteÄnderung;

    public BestellungsZustand(
            int id,
            String beschreibung,
            LocalDateTime letzteÄnderung) {

        this.id = id;
        this.beschreibung = beschreibung;
        this.letzteÄnderung = letzteÄnderung;
    }
}