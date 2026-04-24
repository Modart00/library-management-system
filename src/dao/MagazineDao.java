package dao;

import model.Magazine;
import util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MagazineDao {

    public void saveMagazine(Magazine magazine) {
        String itemSql = "INSERT INTO items(title, type) VALUES(?, ?)";
        String magazineSql = "INSERT INTO magazine(item_id, issue_Number) VALUES(?, ?)";

        try (Connection conn = DatabaseConnection.getConnection()) {

            PreparedStatement psItem = conn.prepareStatement(
                    itemSql,
                    Statement.RETURN_GENERATED_KEYS
            );

            psItem.setString(1, magazine.getTitle());
            psItem.setString(2, "MAGAZINE");

            psItem.executeUpdate();

            ResultSet rs = psItem.getGeneratedKeys();

            if (rs.next()) {
                int itemId = rs.getInt(1);

                PreparedStatement psMagazine = conn.prepareStatement(magazineSql);
                psMagazine.setInt(1, itemId);
                psMagazine.setString(2, magazine.getIssueNumber());

                psMagazine.executeUpdate();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Magazine findMagazineById(int id) {
        String sql = """
            SELECT items.id, items.title, magazine.issue_Number,
            FROM items 
            JOIN magazine  ON items.id = magazine.item_id
            WHERE items.id = ? AND items.type = 'MAGAZINE'
            """;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Magazine(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("issue_Number")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<Magazine> getAllMagazines() {
        List<Magazine> magazineList = new ArrayList<>();

        String sql = """
        SELECT items.id, items.title, magazine.issue_Number
        FROM items
        JOIN magazine ON items.id = magazine.item_id
        WHERE items.type = 'MAGAZINE'
        """;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Magazine magazine = new Magazine(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("issue_Number")
                );

                magazineList.add(magazine);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return magazineList;
    }
}
