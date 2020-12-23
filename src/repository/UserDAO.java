/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository;

import java.util.List;
import model.Book;
import model.User;

/**
 *
 * @author MATRIX COMPUTER
 */
public interface UserDAO {
    public User searchUser(int nim);
    public List<Book> searchUserBook(int nim);
    public List<User> showAllUser();
    public boolean createUser(User user);
    public boolean editUser(User user,int oldNim);
    public boolean deleteUser(int nim);
    public void login(String username,String password);
}
