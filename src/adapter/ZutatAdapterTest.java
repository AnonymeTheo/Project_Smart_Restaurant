package adapter;

import backend.Allergie;
import backend.Zutat;

public class ZutatAdapterTest {

    public static void main(String[] args) {

        ZutatAdapter adapter = new ZutatAdapter();

        // Allergie mit ID 1 = "A"
        // Diese existiert bereits in unserer Datenbank.
        Allergie testAllergie = new Allergie(
                1,
                "A"
        );

        // Testzutat erstellen
        Zutat testZutat = new Zutat(
                0,
                "Testzutat",
                50,
                testAllergie
        );


        // 1. ANLEGEN
        if (!adapter.anlegen(testZutat)) {
            System.err.println(
                    "ERROR: Testzutat konnte nicht angelegt werden."
            );
            return;
        }


        // 2. AUSLESEN
        Zutat geleseneZutat =
                adapter.findeNachId(testZutat.id);

        if (geleseneZutat == null) {
            System.err.println(
                    "ERROR: Testzutat konnte nicht gefunden werden."
            );
            return;
        }


        // 3. Prüfen, ob die Daten korrekt gelesen wurden
        if (!geleseneZutat.bezeichnung.equals("Testzutat")
                || geleseneZutat.bestand != 50
                || geleseneZutat.allergie == null
                || geleseneZutat.allergie.id != 1) {

            System.err.println(
                    "ERROR: Testzutat wurde nicht korrekt ausgelesen."
            );
            return;
        }


        // 4. AKTUALISIEREN
        geleseneZutat.bestand = 100;

        if (!adapter.aktualisieren(geleseneZutat)) {
            System.err.println(
                    "ERROR: Testzutat konnte nicht aktualisiert werden."
            );
            return;
        }


        // 5. Änderung kontrollieren
        Zutat aktualisierteZutat =
                adapter.findeNachId(testZutat.id);

        if (aktualisierteZutat == null
                || aktualisierteZutat.bestand != 100) {

            System.err.println(
                    "ERROR: Änderung wurde nicht korrekt gespeichert."
            );
            return;
        }


        // 6. LÖSCHEN
        if (!adapter.loeschen(testZutat.id)) {
            System.err.println(
                    "ERROR: Testzutat konnte nicht gelöscht werden."
            );
            return;
        }


        // 7. Prüfen, ob wirklich gelöscht wurde
        if (adapter.findeNachId(testZutat.id) != null) {
            System.err.println(
                    "ERROR: Testzutat ist nach dem Löschen noch vorhanden."
            );
        }
    }
}