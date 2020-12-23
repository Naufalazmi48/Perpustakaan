/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository;

import java.util.List;
import model.Book;
import model.History;

/**
 *
 * @author MATRIX COMPUTER
 */
public interface UserWithBookDAO {
    public void borrowBook(int nim,List<Book> idBook);
    public void returnBookBorrowed(int nim,List<Integer> idBook);
    public List<History> getHistory();
    public long countTimeLate(int nim,int idBook);
}