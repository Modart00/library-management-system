package dao;

import model.DVD;
import util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DvdDao {

    public void saveDVD(DVD dvd) {
        String sqlItem = "INSERT INTO items(title, type) VALUES(?, ?)";
        String sqlDVD = "INSERT INTO dvd(item_id, duration, genre) VALUES(?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection()) {

            PreparedStatement psItem = conn.prepareStatement(
                    sqlItem,
                    Statement.RETURN_GENERATED_KEYS
            );

            psItem.setString(1, dvd.getTitle());
            psItem.setString(2, "DVD");

            psItem.executeUpdate();

            ResultSet rs = psItem.getGeneratedKeys();

            if (rs.next()) {
                int itemId = rs.getInt(1);

                PreparedStatement psDvd = conn.prepareStatement(sqlDVD);
                psDvd.setInt(1, itemId);
                psDvd.setInt(2, dvd.getDuration() );
                psDvd.setString(3,dvd.getGenre());

                psDvd.executeUpdate();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public DVD findDVDById(int id) {
        String sql = """
            SELECT items.id, items.title, dvd.duration, dvd.genre
            FROM items 
            JOIN dvd  ON items.id = dvd.item_id
            WHERE items.id = ? AND items.type = 'DVD'
            """;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new DVD(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getInt("duration"),
                        rs.getString("genre")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<DVD> getAllDVDs() {
        List<DVD> DVDList = new ArrayList<>();

        String sql = """
            SELECT items.id, items.title, dvd.duration, dvd.genre
            FROM items 
            JOIN dvd  ON items.id = dvd.item_id
            WHERE items.type = 'DVD'
            """;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                DVD dvd = new DVD(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getInt("duration"),
                        rs.getString("genre")
                );

                DVDList.add(dvd);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return DVDList;
    }

}
