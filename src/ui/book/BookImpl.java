/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.book;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import model.Book;
import repository.Repository;

/**
 *
 * @author MATRIX COMPUTER
 */
public class BookImpl implements BookInterface {

    private final Repository repo;

    public BookImpl(Repository repo) {
        this.repo = repo;
    }

    @Override
    public void read(JTable table) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        for (int i = model.getRowCount() - 1; i >= 0; i--) {
            model.removeRow(i);
        }
        DefaultTableModel dtm = model;
        for (Book book : repo.showAllBook()) {
            if (book.getStock() > 0) {
                Object tempRow[] = new Object[6];
                tempRow[0] = book.getTitle();
                tempRow[1] = book.getAuthor();
                tempRow[2] = book.getYear();
                tempRow[3] = book.getPublisher();
                tempRow[4] = book.getStock();
                tempRow[5] = book.getId();
                dtm.addRow(tempRow);
            }
        }
        table.setModel(dtm);
    }

    @Override
    public boolean update(Book book, int oldId) {
        return repo.updateBook(book, oldId);
    }

    @Override
    public boolean create(Book book) {
        return repo.insertBook(book);
    }

    @Override
    public boolean delete(int idBook) {
        return repo.deleteBook(idBook);
    }

    @Override
    public void search(JTable table, int idBook) {
        Book book = repo.searchBook(idBook);
        if (book != null) {
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            for (int i = model.getRowCount() - 1; i >= 0; i--) {
                model.removeRow(i);
            }
            DefaultTableModel dtm = model;
            Object tempRow[] = new Object[6];
            tempRow[0] = book.getTitle();
            tempRow[1] = book.getAuthor();
            tempRow[2] = book.getYear();
            tempRow[3] = book.getPublisher();
            tempRow[4] = book.getStock();
            tempRow[5] = book.getId();
            dtm.addRow(tempRow);
            table.setModel(dtm);
        }
    }

    @Override
    public void search(JTable table, String title) {
        Book book = repo.searchBook(title);
        if (book != null) {
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            for (int i = model.getRowCount() - 1; i >= 0; i--) {
                model.removeRow(i);
            }
            DefaultTableModel dtm = model;
            Object tempRow[] = new Object[6];
            tempRow[0] = book.getTitle();
            tempRow[1] = book.getAuthor();
            tempRow[2] = book.getYear();
            tempRow[3] = book.getPublisher();
            tempRow[4] = book.getStock();
            tempRow[5] = book.getId();
            dtm.addRow(tempRow);
            table.setModel(dtm);
        }
    }
}
