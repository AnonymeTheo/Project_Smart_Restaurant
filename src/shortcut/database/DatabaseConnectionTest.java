package shortcut.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class DatabaseConnectionTest {

    public static void main(String[] args) {

        try (Connection connection = DatabaseConnection.connect()) {
            pruefeTabellen(connection);

        } catch (Exception e) {
            System.err.println(
                    "ERROR: Datenbankverbindung fehlgeschlagen: "
                            + e.getMessage()
            );
        }
    }


    public static void pruefeTabellen(Connection connection) {

        String[] erwarteteTabellen = {
                "Zutat",
                "MitarbeiterRolle",
                "Tisch",
                "Gericht",
                "Mitarbeiter",
                "Bestellzustand",
                "Bestellung",
                "Bestellposition",
                "GerichtZutatenposition"
        };

        try {

            for (String tabelle : erwarteteTabellen) {

                String sql = "SELECT name FROM sqlite_master "
                        + "WHERE type='table' AND name='"
                        + tabelle + "';";

                try (Statement statement = connection.createStatement();
                     ResultSet resultSet = statement.executeQuery(sql)) {

                    if (!resultSet.next()) {
                        System.err.println(
                                "ERROR: Tabelle fehlt: " + tabelle
                        );
                    }
                }
            }

        } catch (Exception e) {
            System.err.println(
                    "ERROR beim Prüfen der Tabellen: "
                            + e.getMessage()
            );
        }
    }
}