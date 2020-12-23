/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository.local;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public abstract class ConnectionDatabase {

    protected Connection con = null;

    abstract public Connection getConection();

    public void closeConnection() {
        try {
            if (con != null) {
                con.close();
                con = null;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    
}
