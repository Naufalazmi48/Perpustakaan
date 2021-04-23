package repository;

import model.Admin;
import model.User;
import repository.local.ConnectionDatabase;

import javax.swing.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Book;

public class UserRepository implements IQueryRepository<User>{
    private final ConnectionDatabase db;
    private PreparedStatement ps;
    private ResultSet rs;

    public UserRepository(ConnectionDatabase db) {
        this.db = db;
    }

    @Override
    public boolean create(User data) {
        String query = "INSERT INTO `user` (nim,name,address,prodi,numberPhone) VALUES (?, ?, ?, ?,?)";
        try {
            ps = db.getConection().prepareStatement(query);
            ps.setInt(1, data.getNim());
            ps.setString(2, data.getName());
            ps.setString(3, data.getAddress());
            ps.setString(4, data.getProdi());
            ps.setString(5, data.getNumberPhone());
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
    public boolean update(User newData, int oldData) {
        String query = "UPDATE `user` SET nim = ?,name = ?,address = ?,prodi = ?, numberPhone = ? WHERE nim = ?";
        try {
            ps = db.getConection().prepareStatement(query);
            ps.setInt(1, newData.getNim());
            ps.setString(2, newData.getName());
            ps.setString(3, newData.getAddress());
            ps.setString(4, newData.getProdi());
            ps.setString(5, newData.getNumberPhone());
            ps.setInt(6, oldData);
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
    public boolean delete(int id) {
        String query = "DELETE FROM user WHERE nim = ?";
        try {
            ps = db.getConection().prepareStatement(query);
            ps.setInt(1, id);
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
    public User search(int nim) {
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
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Silahkan periksa kembali username dan password yang anda masukkan!");
        } finally {
            db.closeConnection();
        }
    }

    @Override
    public List<User> readAll() {
        List<User> listUser = new ArrayList<>();
        String query = "SELECT * FROM user";
        try {
            Statement s = db.getConection().createStatement();
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
    public long countTimeLate(int nim, int idBook) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void returnBookBorrowed(int nim, List<Integer> idBook) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public User search(String title) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void borrowBook(int nim, List<Book> idBook) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Book> searchUserBook(int nim) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
