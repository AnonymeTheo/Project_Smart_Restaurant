package adapter;

import backend.Zutat;

public class ZutatAdapterTest {

    public static void main(String[] args) {

        ZutatAdapter adapter = new ZutatAdapter();


        // -------------------------
        // TEST 1: Zutat auslesen
        // -------------------------

        Zutat zutat = adapter.findeNachId(1);

        if (zutat == null) {

            System.err.println(
                    "ERROR: Zutat mit ID 1 wurde nicht gefunden."
            );

        } else {

            System.out.println("Zutat gefunden:");

            System.out.println("ID: " + zutat.id);
            System.out.println("Bezeichnung: " + zutat.bezeichnung);
            System.out.println("Bestand: " + zutat.bestand);
            System.out.println("Allergien: " + zutat.allergien);
        }


        // -------------------------
        // TEST 2: Zutat anlegen
        // -------------------------

        Zutat neueZutat = new Zutat(
                0,
                "Testzutat",
                50,
                "Keine"
        );

        boolean erfolgreich = adapter.anlegen(neueZutat);

        if (!erfolgreich) {

            System.err.println(
                    "ERROR: Zutat konnte nicht angelegt werden."
            );

        } else {

            System.out.println(
                    "Zutat wurde erfolgreich angelegt."
            );
        }
    }
}