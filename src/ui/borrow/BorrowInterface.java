/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.borrow;

import java.util.ArrayList;
import java.util.List;
import model.Book;

/**
 *
 * @author MATRIX COMPUTER
 */
public interface BorrowInterface {

    public List<Book> listBook = new ArrayList<>();

    public boolean insertNim(int nim);

    public Book insertIdBook(int nim,int idBook);

    public boolean save(int nim);
}
