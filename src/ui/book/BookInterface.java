/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.book;

import javax.swing.JTable;
import model.Book;

import java.util.List;

/**
 *
 * @author MATRIX COMPUTER
 */
public interface BookInterface {
 List<Book> read();
 boolean update(Book book,int oldId);
 boolean create(Book book);
 boolean delete(int idBook);
 Book search(int idBook);
 Book search(String title);
}
