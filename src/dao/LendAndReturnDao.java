package dao;
import util.DatabaseConnection;
import java.sql.*;
import model.*;
public class LendAndReturnDao {

    public boolean isItemAvailable(int itemId) {
        String sql = """
            SELECT COUNT(*) 
            FROM loans 
            WHERE item_id = ? AND return_date IS NULL
            """;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, itemId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt(1) == 0;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public void lendItem(int itemId, int memberId) {

        if (!isItemAvailable(itemId)) {
            System.out.println("Bu ürün şu anda başka bir üyede!");
            return;
        }

        String sql = "INSERT INTO loans(item_id, member_id) VALUES(?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, itemId);
            ps.setInt(2, memberId);

            ps.executeUpdate();

            System.out.println("Ürün başarıyla ödünç verildi.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void returnItem(int itemId, int memberId) {
        String sql = """
            UPDATE loans 
            SET return_date = NOW()
            WHERE item_id = ? 
              AND member_id = ? 
              AND return_date IS NULL
            """;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, itemId);
            ps.setInt(2, memberId);

            int affectedRows = ps.executeUpdate();

            if (affectedRows > 0) {
                System.out.println("Ürün başarıyla iade edildi.");
            } else {
                System.out.println("Bu üyede aktif olarak böyle bir ürün bulunamadı.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void listActiveLoans() {
        String sql = """
            SELECT 
                items.id AS item_id,
                items.title,
                members.id AS member_id,
                members.name,
                loans.lend_date
            FROM loans
            JOIN items ON loans.item_id = items.id
            JOIN members ON loans.member_id = members.id
            WHERE loans.return_date IS NULL
            """;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                System.out.println(
                        "Item ID: " + rs.getInt("item_id") +
                                " | Başlık: " + rs.getString("title") +
                                " | Üye ID: " + rs.getInt("member_id") +
                                " | Üye: " + rs.getString("name") +
                                " | Verilme tarihi: " + rs.getTimestamp("lend_date")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



}
