/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.history;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import model.Book;
import model.History;
import repository.Repository;

/**
 *
 * @author MATRIX COMPUTER
 */
public class HistoryImpl implements HistoryInterface{

    private final Repository repo;
    public HistoryImpl(Repository repo){
        this.repo = repo;
    }
    
    @Override
    public void read(JTable table) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        for (int i = model.getRowCount() - 1; i >= 0; i--) {
            model.removeRow(i);
        }
        DefaultTableModel dtm = model;
        for (History history : repo.getHistory()) {
            Object tempRow[] = new Object[5];
            tempRow[0] = history.getBook_title();
            tempRow[1] = history.getUser_name();
            tempRow[2] = history.getDate();
            tempRow[3] = history.getStatus();
            tempRow[4] = history.getAdmin_name();
            dtm.addRow(tempRow);
        }
        table.setModel(dtm);
    }
    
}
