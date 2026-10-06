package adapter;

import backend.Mitarbeiter;

public class MitarbeiterAdapterTest {

    public static void main(String[] args) {

        MitarbeiterAdapter adapter = new MitarbeiterAdapter();

        // Testmitarbeiter erstellen
        // "Koch" existiert bereits in MitarbeiterRolle
        Mitarbeiter testMitarbeiter = new Mitarbeiter(
                0,
                "Test Mitarbeiter",
                true,
                "Koch"
        );


        // 1. ANLEGEN
        if (!adapter.anlegen(testMitarbeiter)) {
            System.err.println(
                    "ERROR: Testmitarbeiter konnte nicht angelegt werden."
            );
            return;
        }


        // 2. AUSLESEN
        Mitarbeiter gelesenerMitarbeiter =
                adapter.findeNachId(testMitarbeiter.id);

        if (gelesenerMitarbeiter == null) {
            System.err.println(
                    "ERROR: Testmitarbeiter konnte nicht gefunden werden."
            );
            return;
        }


        // 3. AUSGELESENE DATEN PRÜFEN
        if (!gelesenerMitarbeiter.name.equals("Test Mitarbeiter")
                || !gelesenerMitarbeiter.beschäftigt
                || !gelesenerMitarbeiter.rolle.equals("Koch")) {

            System.err.println(
                    "ERROR: Testmitarbeiter wurde nicht korrekt ausgelesen."
            );
            return;
        }


        // 4. AKTUALISIEREN
        gelesenerMitarbeiter.name = "Test Mitarbeiter Geändert";
        gelesenerMitarbeiter.beschäftigt = false;
        gelesenerMitarbeiter.rolle = "Kellner";

        if (!adapter.aktualisieren(gelesenerMitarbeiter)) {
            System.err.println(
                    "ERROR: Testmitarbeiter konnte nicht aktualisiert werden."
            );
            return;
        }


        // 5. ÄNDERUNGEN AUS DER DATENBANK LESEN
        Mitarbeiter aktualisierterMitarbeiter =
                adapter.findeNachId(testMitarbeiter.id);

        if (aktualisierterMitarbeiter == null) {
            System.err.println(
                    "ERROR: Aktualisierter Mitarbeiter wurde nicht gefunden."
            );
            return;
        }


        // 6. ÄNDERUNGEN PRÜFEN
        if (!aktualisierterMitarbeiter.name.equals(
                "Test Mitarbeiter Geändert")
                || aktualisierterMitarbeiter.beschäftigt
                || !aktualisierterMitarbeiter.rolle.equals("Kellner")) {

            System.err.println(
                    "ERROR: Änderungen wurden nicht korrekt gespeichert."
            );
            return;
        }


        // 7. LÖSCHEN
        if (!adapter.loeschen(testMitarbeiter.id)) {
            System.err.println(
                    "ERROR: Testmitarbeiter konnte nicht gelöscht werden."
            );
            return;
        }


        // 8. PRÜFEN, OB WIRKLICH GELÖSCHT
        if (adapter.findeNachId(testMitarbeiter.id) != null) {
            System.err.println(
                    "ERROR: Testmitarbeiter ist nach dem Löschen noch vorhanden."
            );
        }
    }
}