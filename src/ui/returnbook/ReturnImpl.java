/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.returnbook;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.Book;
import model.User;
import repository.Repository;

/**
 *
 * @author MATRIX COMPUTER
 */
public class ReturnImpl implements ReturnInterface {

    private final Repository repo;
    private long totalDenda = 0;
    private final int denda = 10000;

    public ReturnImpl(Repository repo) {
        this.repo = repo;
    }

    @Override
    public User insertNim(int nim) {
        listBook.clear();
        totalDenda = 0;
        User user = repo.searchUser(nim);
        if (user != null) {
            if (!repo.searchUserBook(nim).isEmpty()) {
                for (Book listBook1 : repo.searchUserBook(nim)) {
                    listBook.add(listBook1);
                }
                return user;
            }
        } else {
            JOptionPane.showMessageDialog(null, "User tidak ditemukan");
        }
        return null;
    }

    @Override
    public boolean save(int nim,List<String> titleBook) {
        if (listBook.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Tidak ada buku yang anda pinjam");
        } else {
            List<Book> listReturn = new ArrayList<>();
            //Cek buku apa saja yang dikembalikan
            for (Book book : listBook) {
                if(titleBook.contains(book.getTitle())){
                    listReturn.add(book);
                }
            }
            List<Integer> idBook = new ArrayList<>();
            String note = "==========================================\n\t\tDAFTAR BUKU YANG DIKEMBALIKAN\n";
            for (Book listBook1 : listReturn) {
                //Ambil jumlah selisih hari expired dgn tgl sekarang
                long lateDay = repo.countTimeLate(nim, listBook1.getId());
                idBook.add(listBook1.getId());
                note += "\t" + listBook1.getTitle();
                if (lateDay > 0) {
                    lateDay /= 7;
                    if(lateDay == 0) lateDay = 1;
                    totalDenda += lateDay * denda;
                    note += "\t\tTerlambat "+lateDay+" Minggu";
                }
                note += "\n";
            }
            if(totalDenda != 0){
                note += "\tTotal Denda : Rp."+totalDenda;
            }
            
            repo.returnBookBorrowed(nim, idBook);
            note += "\n==========================================";
            JOptionPane.showMessageDialog(null, note);
            return true;
        }
        return false;
    }
}
