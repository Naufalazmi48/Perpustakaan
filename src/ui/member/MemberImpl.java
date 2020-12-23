/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.member;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import model.User;
import repository.Repository;

/**
 *
 * @author MATRIX COMPUTER
 */
public class MemberImpl implements MemberInterface {

    private final Repository repo;

    public MemberImpl(Repository repo) {
        this.repo = repo;
    }

    @Override
    public void read(JTable table) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        for (int i = model.getRowCount() - 1; i >= 0; i--) {
            model.removeRow(i);
        }
        DefaultTableModel dtm = model;
        for (User user : repo.showAllUser()) {
            Object tempRow[] = new Object[5];
            tempRow[0] = user.getName();
            tempRow[1] = user.getNim();
            tempRow[2] = user.getProdi();
            tempRow[3] = user.getNumberPhone();
            tempRow[4] = user.getAddress();
            dtm.addRow(tempRow);
        }
        table.setModel(dtm);
    }

    @Override
    public boolean update(User user, int oldNim) {
        return repo.editUser(user, oldNim);
    }

    @Override
    public boolean delete(int nim) {
        return repo.deleteUser(nim);
    }

    @Override
    public boolean create(User user) {
        return repo.createUser(user);
    }

    @Override
    public void search(JTable table, int nim) {
        User user = repo.searchUser(nim);
        if (user != null) {
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            for (int i = model.getRowCount() - 1; i >= 0; i--) {
                model.removeRow(i);
            }
            DefaultTableModel dtm = model;
            Object tempRow[] = new Object[5];
            tempRow[0] = user.getName();
            tempRow[1] = user.getNim();
            tempRow[2] = user.getProdi();
            tempRow[3] = user.getNumberPhone();
            tempRow[4] = user.getAddress();
            dtm.addRow(tempRow);
            table.setModel(dtm);
        }
    }

}