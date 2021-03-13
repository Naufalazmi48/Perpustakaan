/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository;

import java.util.List;
import model.Book;

/**
 *
 * @author MATRIX COMPUTER
 */
public interface IQueryRepository<T> extends ICrudsRepository<T> {

    @Override
    public T search(int id);

    @Override
    public boolean delete(int id);

    @Override
    public boolean update(T newData, int oldData);

    @Override
    public List<T> readAll();

    @Override
    public boolean create(T data);
    
    void login(String username, String password);
    
    long countTimeLate(int nim,int idBook);
    
    void returnBookBorrowed(int nim, List<Integer> idBook);
    
    public T search(String title);
    
    void borrowBook(int nim, List<Book> idBook);
    
    List<Book> searchUserBook(int nim);
}
