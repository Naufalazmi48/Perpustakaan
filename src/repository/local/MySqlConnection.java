/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository.local;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author MATRIX COMPUTER
 */
public class MySqlConnection extends ConnectionDatabase {

    private final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private final String db = "library";   //nama database
    private final String url = "jdbc:mysql://localhost/" + db;   //server link database
    private final String user = "root"; //mysql username
    private final String pass = ""; //mysql password


    @Override
    public Connection getConection() {
        if(con == null){
            try {
                //registrasi driver koneksi
                Class.forName(JDBC_DRIVER);
                //login mysql/koneksi dengan syarat yang ada
                con = DriverManager.getConnection(url, user, pass);
                //test message
                //  System.out.println("Connected!!");
            } catch (ClassNotFoundException | SQLException e) { //kesalahan ditampung di variabel e
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
        }
        return con;
    }
}
