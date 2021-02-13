/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.member;

import javax.swing.JTable;
import model.User;

import java.util.List;

/**
 *
 * @author MATRIX COMPUTER
 */
public interface MemberInterface {
     List<User> read();
     boolean update(User user,int oldNim);
     boolean delete(int nim);
     boolean create(User user);
     User search(int nim);
}
