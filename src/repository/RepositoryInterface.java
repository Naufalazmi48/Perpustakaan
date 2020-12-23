/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository;

import java.util.List;
import model.Book;
import model.History;
import model.User;

/**
 *
 * @author MATRIX COMPUTER
 */
public interface RepositoryInterface {

    public boolean insertBook(Book book);

    public boolean updateBook(Book book, int oldId);

    public boolean deleteBook(int idBook);

    public Book searchBook(String title);

    public Book searchBook(int idBook);

    public List<Book> showAllBook();

    public User searchUser(int nim);

    public List<Book> searchUserBook(int nim);

    public List<User> showAllUser();

    public boolean createUser(User user);

    public boolean editUser(User user, int oldNim);

    public boolean deleteUser(int nim);

    public void login(String username, String password);

    public void borrowBook(int nim, List<Book> idBook);

    public void returnBookBorrowed(int nim, List<Integer> idBook);

    public List<History> getHistory();

    public long countTimeLate(int nim, int idBook);
}
