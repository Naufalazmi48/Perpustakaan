/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.returnbook;

import java.util.ArrayList;
import java.util.List;
import model.Book;
import model.User;

/**
 *
 * @author MATRIX COMPUTER
 */
public interface ReturnInterface {
    public List<Book> listBook = new ArrayList<>();
    public User insertNim(int nim);
    public boolean save(int nim,List<String> titleBook);
}
