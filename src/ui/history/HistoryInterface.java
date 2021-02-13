/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.history;

import model.History;

import javax.swing.JTable;
import java.util.List;

/**
 *
 * @author MATRIX COMPUTER
 */
public interface HistoryInterface {
     List<History> read();
}
