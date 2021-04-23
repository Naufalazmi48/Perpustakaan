/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author MATRIX COMPUTER
 */
public class Admin {
    private static boolean AUTH = false;
    private static String ADMIN_USERNAME;
    private static String ADMIN_NAME;
    private static int ADMIN_ID;

    public boolean isAUTH() {
        return AUTH;
    }

    public void setAUTH(boolean AUTH) {
        Admin.AUTH = AUTH;
    }

    public String getADMIN_USERNAME() {
        return ADMIN_USERNAME;
    }

    public void setADMIN_USERNAME(String ADMIN_USERNAME) {
        Admin.ADMIN_USERNAME = ADMIN_USERNAME;
    }

    public String getADMIN_NAME() {
        return ADMIN_NAME;
    }

    public void setADMIN_NAME(String ADMIN_NAME) {
        Admin.ADMIN_NAME = ADMIN_NAME;
    }

    public int getADMIN_ID() {
        return ADMIN_ID;
    }

    public void setADMIN_ID(int ADMIN_ID) {
        Admin.ADMIN_ID = ADMIN_ID;
    }
}
