package backend;

import java.time.LocalDate;

public class BestellungsZustand{
    public String beschreibung;
    public LocalDate letzteÄnderung;

    public BestellungsZustand(String beschreibung, LocalDate letzteÄnderung){
        this.beschreibung = beschreibung;
        this.letzteÄnderung = letzteÄnderung;
    }
}
