package repository;

import model.History;
import repository.local.ConnectionDatabase;

import javax.swing.*;
import java.sql.*;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class HistoryRepository implements IHistoryRepository{
    private final ConnectionDatabase db;
    private ResultSet rs;

    public HistoryRepository(ConnectionDatabase db) {
        this.db = db;
    }

    @Override
    public List<History> getHistory() {
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
}