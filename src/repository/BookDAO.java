/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository;

import java.util.List;
import javax.swing.JTable;
import model.Book;

/**
 *
 * @author MATRIX COMPUTER
 */
public interface BookDAO {
   public boolean insertBook(Book book);

    public boolean updateBook(Book book,int oldId);

    public boolean deleteBook(int idBook);

    public Book searchBook(String title);

    public Book searchBook(int idBook);

    public List<Book> showAllBook();
}
