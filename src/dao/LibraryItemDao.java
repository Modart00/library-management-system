package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import util.DatabaseConnection;
import model.*;


public class LibraryItemDao {

    public void save(LibraryItem libraryItem){

        try (Connection conn = DatabaseConnection.getConnection()) {

            String sql = "INSERT INTO items(title) VALUES(?)";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, libraryItem.getTitle());
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<LibraryItem> GetAll() {

        List<LibraryItem> ItemList = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "SELECT * FROM items";

            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
            LibraryItem item = new LibraryItem(rs.getInt("id"),
                    rs.getString("title"));
            ItemList.add(item);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ItemList;
    }

    public void deleteById(int id){
        try (Connection conn = DatabaseConnection.getConnection()) {

            String sql = "DELETE FROM items WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1,id);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(LibraryItem item){
        try (Connection conn = DatabaseConnection.getConnection()) {

            String sql = "UPDATE items SET title = ? WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1,item.getTitle());
            ps.setInt(2,item.getId());
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public LibraryItem findById(int id){

        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "SELECT * FROM items WHERE id = ?";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                LibraryItem item = new LibraryItem(rs.getInt("id"),
                        rs.getString("title"));
                return item;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } return null;
    }

}
