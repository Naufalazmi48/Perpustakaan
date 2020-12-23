/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.book;

import javax.swing.JTable;
import model.Book;

/**
 *
 * @author MATRIX COMPUTER
 */
public interface BookInterface {
public void read(JTable table);
public boolean update(Book book,int oldId);
public boolean create(Book book);
public boolean delete(int idBook);
public void search(JTable table,int idBook);
public void search(JTable table,String title);
}
