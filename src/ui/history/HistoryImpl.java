/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.history;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import model.History;
import repository.IHistoryRepository;

import java.util.List;

/**
 *
 * @author MATRIX COMPUTER
 */
public class HistoryImpl implements HistoryInterface{

    private final IHistoryRepository historyRepository;

    public HistoryImpl(IHistoryRepository historyRepository) {
        this.historyRepository = historyRepository;
    }

    @Override
    public List<History> read() {
        return historyRepository.getHistory();
    }
    
}
