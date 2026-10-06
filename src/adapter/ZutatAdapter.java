package adapter;

import backend.Allergie;
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

        String sql =
                "SELECT Zutat.id, Zutat.Bezeichnung, Zutat.Bestand, " +
                        "Allergie.id AS AllergieID, " +
                        "Allergie.Beschreibung AS AllergieBeschreibung " +
                        "FROM Zutat " +
                        "LEFT JOIN Allergie ON Zutat.AllergieID = Allergie.id " +
                        "WHERE Zutat.id = ?";

        try (
                Connection connection = DatabaseConnection.connect();
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {

            statement.setInt(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {

                if (resultSet.next()) {

                    Allergie allergie = null;

                    int allergieId = resultSet.getInt("AllergieID");

                    // Bei NULL liefert getInt() 0.
                    // wasNull() prüft, ob tatsächlich NULL gespeichert war.
                    if (!resultSet.wasNull()) {
                        allergie = new Allergie(
                                allergieId,
                                resultSet.getString("AllergieBeschreibung")
                        );
                    }

                    return new Zutat(
                            resultSet.getInt("id"),
                            resultSet.getString("Bezeichnung"),
                            resultSet.getInt("Bestand"),
                            allergie
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


    // Neue Zutat anlegen
    public boolean anlegen(Zutat zutat) {

        String sql =
                "INSERT INTO Zutat " +
                        "(Bezeichnung, Bestand, AllergieID) " +
                        "VALUES (?, ?, ?)";

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

            if (zutat.allergie != null) {
                statement.setInt(3, zutat.allergie.id);
            } else {
                statement.setNull(3, java.sql.Types.INTEGER);
            }

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

        String sql =
                "UPDATE Zutat " +
                        "SET Bezeichnung = ?, Bestand = ?, AllergieID = ? " +
                        "WHERE id = ?";

        try (
                Connection connection = DatabaseConnection.connect();
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {

            statement.setString(1, zutat.bezeichnung);
            statement.setInt(2, zutat.bestand);

            if (zutat.allergie != null) {
                statement.setInt(3, zutat.allergie.id);
            } else {
                statement.setNull(3, java.sql.Types.INTEGER);
            }

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