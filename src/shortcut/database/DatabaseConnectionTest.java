package shortcut.database;

import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseConnectionTest {

    public static void main(String[] args) {

        try (Connection connection = DatabaseConnection.connect()) {
            System.out.println("Verbindung zur Datenbank erfolgreich!");
        } catch (SQLException e) {
            System.out.println("Datenbankverbindung fehlgeschlagen:");
            e.printStackTrace();
        }
    }
}