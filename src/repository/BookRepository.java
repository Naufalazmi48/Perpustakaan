package repository;

import model.Admin;
import model.Book;
import repository.local.ConnectionDatabase;

import javax.swing.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BookRepository implements IQueryRepository<Book>{
    private final ConnectionDatabase db;
    private PreparedStatement ps;
    private ResultSet rs;

    public BookRepository(ConnectionDatabase db) {
        this.db = db;
    }

    @Override
    public List<Book> readAll() {
        List<Book> listBook = new ArrayList<>();
        String query = "SELECT * FROM book";
        try {
            Statement s = db.getConection().createStatement();
            rs = s.executeQuery(query);
            while (rs.next()) {
                Book book = new Book();
                book.setId(rs.getInt("id"));
                book.setTitle(rs.getString("title"));
                book.setYear(rs.getInt("year"));
                book.setPublisher(rs.getString("publisher"));
                book.setAuthor(rs.getString("author"));
                book.setStock(rs.getInt("stock"));
                listBook.add(book);
            }
            return listBook;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        } finally {
            db.closeConnection();
        }
        return null;
    }

    @Override
    public List<Book> searchUserBook(int nim) {
        String query = "SELECT * FROM userwithbook JOIN book ON (userwithbook.book_id = book.id) WHERE user_nim = ?";
        ArrayList<Book> listBook = new ArrayList<>();
        try {
            ps = db.getConection().prepareStatement(query);
            ps.setInt(1, nim);
            rs = ps.executeQuery();

            while (rs.next()) {
                Book book = new Book();
                book.setId(rs.getInt("book.id"));
                book.setTitle(rs.getString("title"));
                book.setYear(rs.getInt("year"));
                book.setPublisher(rs.getString("publisher"));
                book.setAuthor(rs.getString("author"));
                listBook.add(book);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        } finally {
            db.closeConnection();
        }
        return listBook;
    }

    @Override
    public void borrowBook(int nim, List<Book> idBook) {
        String query = "INSERT INTO `userwithbook` (admin_id,user_nim,book_id,date,expired) VALUES (?, ?, ?, NOW(), (NOW() + INTERVAL 7 DAY))";
        try {
            for (Book idBook1 : idBook) {
                ps = db.getConection().prepareStatement(query);
                ps.setInt(1, Admin.ADMIN_ID);
                ps.setInt(2, nim);
                ps.setInt(3, idBook1.getId());
                ps.executeUpdate();
            }

            JOptionPane.showMessageDialog(null, "Berhasil meminjam");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        } finally {
            db.closeConnection();
        }
    }

    @Override
    public void returnBookBorrowed(int nim, List<Integer> idBook) {
        String query = "DELETE FROM `userwithbook` WHERE user_nim = ? AND book_id = ?";
        try {
            for (Integer idBook1 : idBook) {
                ps = db.getConection().prepareStatement(query);
                ps.setInt(1, nim);
                ps.setInt(2, idBook1);
                ps.executeUpdate();
            }
            JOptionPane.showMessageDialog(null, "Buku berhasil dikembalikan!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        } finally {
            db.closeConnection();
        }
    }

    @Override
    public Book search(int id) {
        String query = "SELECT * FROM `book` WHERE id = ?";
        try {
            ps = db.getConection().prepareStatement(query);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            if (rs.next()) {
                Book book = new Book();
                book.setId(rs.getInt("id"));
                book.setTitle(rs.getString("title"));
                book.setYear(rs.getInt("year"));
                book.setPublisher(rs.getString("publisher"));
                book.setAuthor(rs.getString("author"));
                book.setStock(rs.getInt("stock"));
                return book;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Buku tidak ditemukan");
        } finally {
            db.closeConnection();
        }
        return null;
    }

    @Override
    public boolean create(Book data) {
        String query = "INSERT INTO `book` (title,author,year,publisher,stock) VALUES (?, ?, ?, ?,?)";
        try {
            ps = db.getConection().prepareStatement(query);
            ps.setString(1, data.getTitle());
            ps.setString(2, data.getAuthor());
            ps.setInt(3, data.getYear());
            ps.setString(4, data.getPublisher());
            ps.setInt(5, data.getStock());
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data buku berhasil ditambahkan!");
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
            return false;
        } finally {
            db.closeConnection();
        }
    }

    @Override
    public boolean update(Book book, int oldId) {
        String query = "UPDATE `book` SET title = ?,author = ?,year = ?,publisher = ?, stock = ? WHERE id = ?";
        try {
            ps = db.getConection().prepareStatement(query);
            ps.setString(1, book.getTitle());
            ps.setString(2, book.getAuthor());
            ps.setInt(3, book.getYear());
            ps.setString(4, book.getPublisher());
            ps.setInt(5, book.getStock());
            ps.setInt(6, oldId);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data berhasil diperbarui!");
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Data gagal diperbarui");
        } finally {
            db.closeConnection();
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        String query = "DELETE FROM `book` WHERE id = ?";
        try {
            ps = db.getConection().prepareStatement(query);
            ps.setInt(1, id);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data berhasil dihapus!");
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Data gagal dihapus");
            return false;
        } finally {
            db.closeConnection();
        }
    }

    @Override
    public Book search(String data) {
        String query = "SELECT * FROM `book` WHERE title = ?";
        try {
            ps = db.getConection().prepareStatement(query);
            ps.setString(1, data);
            rs = ps.executeQuery();

            if (rs.next()) {
                Book book = new Book();
                book.setId(rs.getInt("id"));
                book.setTitle(rs.getString("title"));
                book.setYear(rs.getInt("year"));
                book.setPublisher(rs.getString("publisher"));
                book.setAuthor(rs.getString("author"));
                book.setStock(rs.getInt("stock"));
                return book;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Data tidak ditemukan");
        } finally {
            db.closeConnection();
        }
        return null;
    }

    @Override
    public void login(String username, String password) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public long countTimeLate(int nim, int idBook) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
