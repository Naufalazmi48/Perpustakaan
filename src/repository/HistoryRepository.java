package repository;

import model.History;
import repository.local.ConnectionDatabase;

import javax.swing.*;
import java.sql.*;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import model.Book;

public class HistoryRepository implements IQueryRepository<History>{
    private final ConnectionDatabase db;
    private ResultSet rs;

    public HistoryRepository(ConnectionDatabase db) {
        this.db = db;
    }

    @Override
    public List<History> readAll() {
        String query = "SELECT admin.name,date,status,book.title,user.name FROM `log` JOIN book ON (log.book_id = book.id) JOIN admin ON (log.admin_id = admin.id) JOIN user ON (log.user_nim = user.nim) ";
        List<History> listHistory = new ArrayList<>();
        try {
            Statement s = db.getConection().createStatement();
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
    public long countTimeLate(int nim, int idBook) {
        String query = "SELECT expired FROM userwithbook WHERE user_nim = ? and book_id = ?";
        try {
            PreparedStatement ps = db.getConection().prepareStatement(query);
            ps.setInt(1, nim);
            ps.setInt(2, idBook);
            rs = ps.executeQuery();
            rs.next();
            Timestamp ts = rs.getTimestamp("expired");
            Date expired = new Date(ts.getTime());
            return ChronoUnit.DAYS.between(expired.toInstant(),new Date(Calendar.DATE).toInstant());
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        } finally {
            db.closeConnection();
        }
        return 0;
    }

    @Override
    public History search(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(History newData, int oldData) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean create(History data) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean login(String username, String password) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean returnBookBorrowed(int nim, List<Integer> idBook) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public History search(String title) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean borrowBook(int nim, List<Book> idBook) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Book> searchUserBook(int nim) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}