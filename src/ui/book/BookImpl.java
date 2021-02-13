/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.book;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import model.Book;
import repository.IBookRepository;

import java.util.List;

/**
 * @author MATRIX COMPUTER
 */
public class BookImpl implements BookInterface {

    private final IBookRepository bookRepository;

    public BookImpl(IBookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }


    @Override
    public List<Book> read() {
        return bookRepository.showAllBook();
    }

    @Override
    public boolean update(Book book, int oldId) {
        return bookRepository.update(book, oldId);
    }

    @Override
    public boolean create(Book book) {
        return bookRepository.create(book);
    }

    @Override
    public boolean delete(int idBook) {
        return bookRepository.delete(idBook);
    }

    @Override
    public Book search(int idBook) {
        return bookRepository.searchBook(idBook);
    }

    @Override
    public Book search(String title) {
        return bookRepository.searchBook(title);
    }
}
