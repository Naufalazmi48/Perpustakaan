/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.borrow;

import javax.swing.JOptionPane;
import model.Book;
import model.User;
import repository.IBookRepository;
import repository.IUserRepository;

/**
 *
 * @author MATRIX COMPUTER
 */
public class BorrowImpl implements BorrowInterface {

    private final IUserRepository userRepository;
    private final IBookRepository bookRepository;

    public BorrowImpl(IUserRepository userRepository, IBookRepository bookRepository) {
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
    }
    private int limitBorrow;

    @Override
    public boolean insertNim(int nim) {
        listBook.clear();
        limitBorrow = 3;
        User user = userRepository.searchUser(nim);
        if (user != null) {
            var countBook = bookRepository.searchUserBook(nim).size();
            if (countBook == 3) {
                JOptionPane.showMessageDialog(null, "Anda sudah meminjam 3 buku, silahkan kembalikan terlebih dahulu");
            } else {
                limitBorrow -= countBook;
                JOptionPane.showMessageDialog(null, "Peminjam atas nama : " + user.getName());
                return true;
            }
        }
        return false;
    }

    @Override
    public Book insertIdBook(int nim, int idBook) {
        for (Book listBook1 : bookRepository.searchUserBook(nim)) {
            if (listBook1.getId() == idBook) {
                JOptionPane.showMessageDialog(null, "Anda sudah meminjam buku ini sebelumnya, silahkan kembalikan terlebih dahulu");
                return null;
            }
        }
        if (limitBorrow != 0) {
            Book book = bookRepository.searchBook(idBook);
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
            bookRepository.borrowBook(nim, listBook);
        }
        return true;
    }
}
