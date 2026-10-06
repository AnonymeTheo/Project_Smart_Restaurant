package adapter;

import backend.Mitarbeiter;
import shortcut.database.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MitarbeiterAdapter {

    // Mitarbeiter anhand der ID auslesen
    public Mitarbeiter findeNachId(int id) {

        String sql =
                "SELECT Mitarbeiter.id, " +
                        "Mitarbeiter.Name, " +
                        "Mitarbeiter.Beschäftigt, " +
                        "MitarbeiterRolle.Beschreibung AS Rolle " +
                        "FROM Mitarbeiter " +
                        "LEFT JOIN MitarbeiterRolle " +
                        "ON Mitarbeiter.MitarbeiterRolleID = MitarbeiterRolle.id " +
                        "WHERE Mitarbeiter.id = ?";

        try (
                Connection connection = DatabaseConnection.connect();
                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            statement.setInt(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {

                if (resultSet.next()) {

                    return new Mitarbeiter(
                            resultSet.getInt("id"),
                            resultSet.getString("Name"),
                            resultSet.getBoolean("Beschäftigt"),
                            resultSet.getString("Rolle")
                    );
                }
            }

        } catch (SQLException e) {
            System.err.println(
                    "ERROR beim Auslesen des Mitarbeiters: "
                            + e.getMessage()
            );
        }

        return null;
    }


    // Neuen Mitarbeiter anlegen
    public boolean anlegen(Mitarbeiter mitarbeiter) {

        Integer rolleId = findeRolleId(mitarbeiter.rolle);

        if (rolleId == null) {
            System.err.println(
                    "ERROR: Mitarbeiterrolle nicht gefunden: "
                            + mitarbeiter.rolle
            );
            return false;
        }

        String sql =
                "INSERT INTO Mitarbeiter " +
                        "(Name, Beschäftigt, MitarbeiterRolleID) " +
                        "VALUES (?, ?, ?)";

        try (
                Connection connection = DatabaseConnection.connect();
                PreparedStatement statement =
                        connection.prepareStatement(
                                sql,
                                Statement.RETURN_GENERATED_KEYS
                        )
        ) {

            statement.setString(1, mitarbeiter.name);
            statement.setBoolean(2, mitarbeiter.beschäftigt);
            statement.setInt(3, rolleId);

            int betroffeneZeilen = statement.executeUpdate();

            if (betroffeneZeilen != 1) {
                return false;
            }

            try (ResultSet keys = statement.getGeneratedKeys()) {

                if (keys.next()) {
                    mitarbeiter.id = keys.getInt(1);
                    return true;
                }
            }

        } catch (SQLException e) {
            System.err.println(
                    "ERROR beim Anlegen des Mitarbeiters: "
                            + e.getMessage()
            );
        }

        return false;
    }


    // Mitarbeiter aktualisieren
    public boolean aktualisieren(Mitarbeiter mitarbeiter) {

        Integer rolleId = findeRolleId(mitarbeiter.rolle);

        if (rolleId == null) {
            System.err.println(
                    "ERROR: Mitarbeiterrolle nicht gefunden: "
                            + mitarbeiter.rolle
            );
            return false;
        }

        String sql =
                "UPDATE Mitarbeiter " +
                        "SET Name = ?, Beschäftigt = ?, " +
                        "MitarbeiterRolleID = ? " +
                        "WHERE id = ?";

        try (
                Connection connection = DatabaseConnection.connect();
                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            statement.setString(1, mitarbeiter.name);
            statement.setBoolean(2, mitarbeiter.beschäftigt);
            statement.setInt(3, rolleId);
            statement.setInt(4, mitarbeiter.id);

            return statement.executeUpdate() == 1;

        } catch (SQLException e) {
            System.err.println(
                    "ERROR beim Aktualisieren des Mitarbeiters: "
                            + e.getMessage()
            );

            return false;
        }
    }


    // Mitarbeiter anhand der ID löschen
    public boolean loeschen(int id) {

        String sql =
                "DELETE FROM Mitarbeiter WHERE id = ?";

        try (
                Connection connection = DatabaseConnection.connect();
                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            statement.setInt(1, id);

            return statement.executeUpdate() == 1;

        } catch (SQLException e) {
            System.err.println(
                    "ERROR beim Löschen des Mitarbeiters: "
                            + e.getMessage()
            );

            return false;
        }
    }


    // ID einer Mitarbeiterrolle anhand der Beschreibung suchen
    private Integer findeRolleId(String rolle) {

        String sql =
                "SELECT id FROM MitarbeiterRolle " +
                        "WHERE Beschreibung = ?";

        try (
                Connection connection = DatabaseConnection.connect();
                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            statement.setString(1, rolle);

            try (ResultSet resultSet = statement.executeQuery()) {

                if (resultSet.next()) {
                    return resultSet.getInt("id");
                }
            }

        } catch (SQLException e) {
            System.err.println(
                    "ERROR beim Suchen der Mitarbeiterrolle: "
                            + e.getMessage()
            );
        }

        return null;
    }
}