package adapter;

import backend.Zutat;
import shortcut.database.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ZutatAdapter {

    // Zutat anhand der ID auslesen
    public Zutat findeNachId(int id) {

        String sql = "SELECT * FROM Zutat WHERE id = ?";

        try (
                Connection connection = DatabaseConnection.connect();
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {

            statement.setInt(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {

                if (resultSet.next()) {

                    return new Zutat(
                            resultSet.getInt("id"),
                            resultSet.getString("Bezeichnung"),
                            resultSet.getInt("Bestand"),
                            resultSet.getString("Allergien")
                    );
                }
            }

        } catch (SQLException e) {
            System.err.println(
                    "ERROR beim Auslesen der Zutat: "
                            + e.getMessage()
            );
        }

        return null;
    }


    // Neue Zutat anlegen und erzeugte ID übernehmen
    public boolean anlegen(Zutat zutat) {

        String sql = "INSERT INTO Zutat "
                + "(Bezeichnung, Bestand, Allergien) "
                + "VALUES (?, ?, ?)";

        try (
                Connection connection = DatabaseConnection.connect();
                PreparedStatement statement =
                        connection.prepareStatement(
                                sql,
                                Statement.RETURN_GENERATED_KEYS
                        )
        ) {

            statement.setString(1, zutat.bezeichnung);
            statement.setInt(2, zutat.bestand);
            statement.setString(3, zutat.allergien);

            int betroffeneZeilen = statement.executeUpdate();

            if (betroffeneZeilen != 1) {
                return false;
            }

            try (ResultSet keys = statement.getGeneratedKeys()) {

                if (keys.next()) {
                    zutat.id = keys.getInt(1);
                    return true;
                }
            }

        } catch (SQLException e) {
            System.err.println(
                    "ERROR beim Anlegen der Zutat: "
                            + e.getMessage()
            );
        }

        return false;
    }


    // Vorhandene Zutat aktualisieren
    public boolean aktualisieren(Zutat zutat) {

        String sql = "UPDATE Zutat "
                + "SET Bezeichnung = ?, Bestand = ?, Allergien = ? "
                + "WHERE id = ?";

        try (
                Connection connection = DatabaseConnection.connect();
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {

            statement.setString(1, zutat.bezeichnung);
            statement.setInt(2, zutat.bestand);
            statement.setString(3, zutat.allergien);
            statement.setInt(4, zutat.id);

            return statement.executeUpdate() == 1;

        } catch (SQLException e) {
            System.err.println(
                    "ERROR beim Aktualisieren der Zutat: "
                            + e.getMessage()
            );

            return false;
        }
    }


    // Zutat anhand der ID löschen
    public boolean loeschen(int id) {

        String sql = "DELETE FROM Zutat WHERE id = ?";

        try (
                Connection connection = DatabaseConnection.connect();
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {

            statement.setInt(1, id);

            return statement.executeUpdate() == 1;

        } catch (SQLException e) {
            System.err.println(
                    "ERROR beim Löschen der Zutat: "
                            + e.getMessage()
            );

            return false;
        }
    }
}