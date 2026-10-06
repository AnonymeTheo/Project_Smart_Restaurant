package adapter;

import backend.Zutat;

public class ZutatAdapterTest {

    public static void main(String[] args) {

        ZutatAdapter adapter = new ZutatAdapter();

        Zutat zutat = adapter.findeNachId(1);

        if (zutat == null) {
            System.err.println("ERROR: Zutat wurde nicht gefunden.");
            return;
        }

        System.out.println("ID: " + zutat.id);
        System.out.println("Bezeichnung: " + zutat.bezeichnung);
        System.out.println("Bestand: " + zutat.bestand);
        System.out.println("Allergien: " + zutat.allergien);
    }
}