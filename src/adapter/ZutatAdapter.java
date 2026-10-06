package adapter;

import backend.Zutat;
import shortcut.database.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ZutatAdapter {

    // Zutat anhand der ID aus der Datenbank lesen
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


    // Neue Zutat in der Datenbank anlegen
    public boolean anlegen(Zutat zutat) {

        String sql = "INSERT INTO Zutat "
                + "(Bezeichnung, Bestand, Allergien) "
                + "VALUES (?, ?, ?)";

        try (
                Connection connection = DatabaseConnection.connect();
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {

            statement.setString(1, zutat.bezeichnung);
            statement.setInt(2, zutat.bestand);
            statement.setString(3, zutat.allergien);

            int betroffeneZeilen = statement.executeUpdate();

            return betroffeneZeilen == 1;

        } catch (SQLException e) {
            System.err.println(
                    "ERROR beim Anlegen der Zutat: "
                            + e.getMessage()
            );

            return false;
        }
    }
}