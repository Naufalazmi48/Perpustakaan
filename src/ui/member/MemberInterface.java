/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.member;

import javax.swing.JTable;
import model.User;

/**
 *
 * @author MATRIX COMPUTER
 */
public interface MemberInterface {
    public void read(JTable table);
    public boolean update(User user,int oldNim);
    public boolean delete(int nim);
    public boolean create(User user);
    public void search(JTable table,int nim);
}
