package adapter;

import backend.Zutat;

public class ZutatAdapterTest {

    public static void main(String[] args) {

        ZutatAdapter adapter = new ZutatAdapter();

        // 1. Testzutat erstellen
        Zutat testZutat = new Zutat(
                0,
                "Testzutat",
                50,
                "Keine"
        );


        // 2. ANLEGEN testen
        if (!adapter.anlegen(testZutat)) {
            System.err.println(
                    "ERROR: Testzutat konnte nicht angelegt werden."
            );
            return;
        }

        // SQLite hat jetzt die echte ID in testZutat.id geschrieben


        // 3. AUSLESEN testen
        Zutat geleseneZutat =
                adapter.findeNachId(testZutat.id);

        if (geleseneZutat == null) {
            System.err.println(
                    "ERROR: Angelegte Zutat konnte nicht gefunden werden."
            );
            return;
        }


        // 4. AKTUALISIEREN testen
        geleseneZutat.bestand = 100;

        if (!adapter.aktualisieren(geleseneZutat)) {
            System.err.println(
                    "ERROR: Zutat konnte nicht aktualisiert werden."
            );
            return;
        }


        // 5. Änderung überprüfen
        Zutat aktualisierteZutat =
                adapter.findeNachId(testZutat.id);

        if (aktualisierteZutat == null
                || aktualisierteZutat.bestand != 100) {

            System.err.println(
                    "ERROR: Änderung wurde nicht korrekt gespeichert."
            );
            return;
        }


        // 6. LÖSCHEN testen
        if (!adapter.loeschen(testZutat.id)) {
            System.err.println(
                    "ERROR: Testzutat konnte nicht gelöscht werden."
            );
            return;
        }


        // 7. Prüfen, ob sie wirklich gelöscht wurde
        if (adapter.findeNachId(testZutat.id) != null) {
            System.err.println(
                    "ERROR: Testzutat ist nach dem Löschen noch vorhanden."
            );
        }
    }
}