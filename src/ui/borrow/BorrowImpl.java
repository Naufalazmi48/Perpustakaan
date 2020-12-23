/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.borrow;

import javax.swing.JOptionPane;
import model.Book;
import model.User;
import repository.Repository;

/**
 *
 * @author MATRIX COMPUTER
 */
public class BorrowImpl implements BorrowInterface {

    private final Repository repo;

    public BorrowImpl(Repository repo) {
        this.repo = repo;
    }
    private int limitBorrow;

    @Override
    public boolean insertNim(int nim) {
        listBook.clear();
        limitBorrow = 3;
        User user = repo.searchUser(nim);
        if (user != null) {
            if (repo.searchUserBook(nim).size() == 3) {
                JOptionPane.showMessageDialog(null, "Anda sudah meminjam 3 buku, silahkan kembalikan terlebih dahulu");
            } else {
                limitBorrow -= repo.searchUserBook(nim).size();
                JOptionPane.showMessageDialog(null, "Peminjam atas nama : " + user.getName());
                return true;
            }
        }
        return false;
    }

    @Override
    public Book insertIdBook(int nim, int idBook) {
        for (Book listBook1 : repo.searchUserBook(nim)) {
            if (listBook1.getId() == idBook) {
                JOptionPane.showMessageDialog(null, "Anda sudah meminjam buku ini sebelumnya, silahkan kembalikan terlebih dahulu");
                return null;
            }
        }
        if (limitBorrow != 0) {
            Book book = repo.searchBook(idBook);
            if (book != null && book.getStock() > 0) {
                limitBorrow--;
                listBook.add(book);
                return book;
            } else {
                JOptionPane.showMessageDialog(null, "Data buku yang anda cari tidak ditemukan");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Anda meminjam buku lebih dari 3 buku, silahkan kembalikan terlebih dahulu");
        }
        return null;
    }

    @Override
    public boolean save(int nim) {
        if (listBook.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Gagal menyimpan");
            return false;
        } else {
            repo.borrowBook(nim, listBook);
        }
        return true;
    }
}
