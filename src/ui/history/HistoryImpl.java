/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.history;

import model.History;

import java.util.List;
import repository.IQueryRepository;

/**
 *
 * @author MATRIX COMPUTER
 */
public class HistoryImpl implements HistoryInterface{

    private final IQueryRepository<History> historyRepository;

    public HistoryImpl(IQueryRepository<History> historyRepository) {
        this.historyRepository = historyRepository;
    }

    @Override
    public List<History> read() {
        return historyRepository.readAll();
    }
    
}
