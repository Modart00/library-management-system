package dao;

import model.Book;
import util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDao {

    public void saveBook(Book book) {
        String sqlItem = "INSERT INTO items(title, type) VALUES(?, ?)";
        String sqlBook = "INSERT INTO books(item_id, author, page_count) VALUES(?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection()) {

            PreparedStatement psItem = conn.prepareStatement(
                    sqlItem,
                    Statement.RETURN_GENERATED_KEYS
            );

            psItem.setString(1, book.getTitle());
            psItem.setString(2, "BOOK");

            psItem.executeUpdate();

            ResultSet rs = psItem.getGeneratedKeys();

            if (rs.next()) {
                int itemId = rs.getInt(1);

                PreparedStatement psBook = conn.prepareStatement(sqlBook);
                psBook.setInt(1, itemId);
                psBook.setString(2, book.getAuthor());
                psBook.setInt(3, book.getPageCount());

                psBook.executeUpdate();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Book findBookById(int id) {
        String sql = """
            SELECT items.id, items.title, books.author, books.page_count
            FROM items 
            JOIN books  ON items.id = books.item_id
            WHERE items.id = ? AND items.type = 'BOOK'
            """;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Book(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getInt("page_count")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<Book> getAllBooks() {
        List<Book> bookList = new ArrayList<>();

        String sql = """
            SELECT items.id, items.title, books.author, books.page_count
            FROM items 
            JOIN books  ON items.id = books.item_id
            WHERE items.type = 'BOOK'
            """;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Book book = new Book(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getInt("page_count")
                );

                bookList.add(book);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return bookList;
    }

}
