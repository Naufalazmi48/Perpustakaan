/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import model.Admin;
import model.Book;
import model.History;
import model.User;
import repository.local.ConnectionDatabase;

/**
 *
 * @author MATRIX COMPUTER
 */
public class Repository implements UserWithBookDAO, BookDAO, UserDAO {

    private final ConnectionDatabase db;
    private Statement s;
    private PreparedStatement ps;
    private ResultSet rs;

    public Repository(ConnectionDatabase db) {
        this.db = db;
    }

    @Override
    public void login(String username, String password) {
        String query = "SELECT * FROM admin WHERE username = ? and password = ?";
        try {
            ps = db.getConection().prepareStatement(query);
            ps.setString(1, username);
            ps.setString(2, password);
            rs = ps.executeQuery();

            rs.next();
            Admin.ADMIN_USERNAME = rs.getString("username");
            Admin.ADMIN_NAME = rs.getString("name");
            Admin.ADMIN_ID = rs.getInt("id");
            Admin.AUTH = true;
            JOptionPane.showMessageDialog(null, "Anda berhasil login sebagai " + Admin.ADMIN_USERNAME);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Silahkan periksa kembali username dan password yang anda masukkan!");
        } finally {
            db.closeConnection();
        }
    }

    @Override
    public List<User> showAllUser() {
        List<User> listUser = new ArrayList<>();
        String query = "SELECT * FROM user";
        try {
            s = db.getConection().createStatement();
            rs = s.executeQuery(query);
            while (rs.next()) {
                User user = new User();
                user.setNim(rs.getInt("nim"));
                user.setName(rs.getString("name"));
                user.setAddress(rs.getString("address"));
                user.setNumberPhone(rs.getString("numberPhone"));
                user.setProdi(rs.getString("prodi"));
                listUser.add(user);
            }
            return listUser;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        } finally {
            db.closeConnection();
        }
        return null;
    }

    @Override
    public boolean createUser(User user) {
        String query = "INSERT INTO `user` (nim,name,address,prodi,numberPhone) VALUES (?, ?, ?, ?,?)";
        try {
            ps = db.getConection().prepareStatement(query);
            ps.setInt(1, user.getNim());
            ps.setString(2, user.getName());
            ps.setString(3, user.getAddress());
            ps.setString(4, user.getProdi());
            ps.setString(5, user.getNumberPhone());
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data user berhasil ditambahkan!");
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
            return false;
        } finally {
            db.closeConnection();
        }
    }

    @Override
    public boolean editUser(User user, int oldNim) {
        String query = "UPDATE `user` SET nim = ?,name = ?,address = ?,prodi = ?, numberPhone = ? WHERE nim = ?";
        try {
            ps = db.getConection().prepareStatement(query);
            ps.setInt(1, user.getNim());
            ps.setString(2, user.getName());
            ps.setString(3, user.getAddress());
            ps.setString(4, user.getProdi());
            ps.setString(5, user.getNumberPhone());
            ps.setInt(6, oldNim);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data user berhasil diubah!");
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Data user gagal diubah");
            return false;
        } finally {
            db.closeConnection();
        }
    }

    @Override
    public boolean deleteUser(int nim) {
        String query = "DELETE FROM user WHERE nim = ?";
        try {
            ps = db.getConection().prepareStatement(query);
            ps.setInt(1, nim);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data user berhasil dihapus!");
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Data user gagal dihapus");
            return false;
        } finally {
            db.closeConnection();
        }
    }

    @Override
    public User searchUser(int nim) {
        String query = "SELECT * FROM user WHERE nim = ?";
        try {
            ps = db.getConection().prepareStatement(query);
            ps.setInt(1, nim);
            rs = ps.executeQuery();

            rs.next();
            User user = new User();
            user.setNim(rs.getInt("nim"));
            user.setName(rs.getString("name"));
            user.setAddress(rs.getString("address"));
            user.setNumberPhone(rs.getString("numberPhone"));
            user.setProdi(rs.getString("prodi"));
            return user;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "User tidak terdaftar");
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
    public boolean insertBook(Book book) {
        String query = "INSERT INTO `book` (title,author,year,publisher,stock) VALUES (?, ?, ?, ?,?)";
        try {
            ps = db.getConection().prepareStatement(query);
            ps.setString(1, book.getTitle());
            ps.setString(2, book.getAuthor());
            ps.setInt(3, book.getYear());
            ps.setString(4, book.getPublisher());
            ps.setInt(5, book.getStock());
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
    public boolean updateBook(Book book, int oldId) {
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
    public boolean deleteBook(int idBook) {
        String query = "DELETE FROM `book` WHERE id = ?";
        try {
            ps = db.getConection().prepareStatement(query);
            ps.setInt(1, idBook);
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
    public Book searchBook(String title) {
        String query = "SELECT * FROM `book` WHERE title = ?";
        try {
            ps = db.getConection().prepareStatement(query);
            ps.setString(1, title);
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
    public List<Book> showAllBook() {
        List<Book> listBook = new ArrayList<>();
        String query = "SELECT * FROM book";
        try {
            s = db.getConection().createStatement();
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
    public Book searchBook(int idBook) {
        String query = "SELECT * FROM `book` WHERE id = ?";
        try {
            ps = db.getConection().prepareStatement(query);
            ps.setInt(1, idBook);
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
    public List<History> getHistory() {
        String query = "SELECT admin.name,date,status,book.title,user.name FROM `log` JOIN book ON (log.book_id = book.id) JOIN admin ON (log.admin_id = admin.id) JOIN user ON (log.user_nim = user.nim) ";
        List<History> listHistory = new ArrayList<>();
        try {
            s = db.getConection().createStatement();
            rs = s.executeQuery(query);
            while (rs.next()) {
                History history = new History();
                history.setAdmin_name(rs.getString("admin.name"));
                history.setBook_title(rs.getString("book.title"));
                history.setUser_name(rs.getString("user.name"));
                history.setStatus(rs.getString("status"));
                history.setDate(rs.getDate("date"));
                listHistory.add(history);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        } finally {
            db.closeConnection();
        }
        return listHistory;
    }

    @Override
    public long countTimeLate(int nim,int idBook) {
         String query = "SELECT expired FROM userwithbook WHERE user_nim = ? and book_id = ?";
        try {
            ps = db.getConection().prepareStatement(query);
            ps.setInt(1, nim);
            ps.setInt(2, idBook);
            rs = ps.executeQuery();
            rs.next();
            Timestamp ts = rs.getTimestamp("expired");
            Date expired = new Date(ts.getTime());
            return ChronoUnit.DAYS.between(expired.toInstant(),new Date().toInstant());
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        } finally {
            db.closeConnection();
        }
        return 0;
    }
}