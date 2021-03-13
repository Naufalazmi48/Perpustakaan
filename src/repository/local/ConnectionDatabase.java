/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository.local;
import java.sql.Connection;

public interface ConnectionDatabase {

      Connection getConection();

     void closeConnection();
    
}
