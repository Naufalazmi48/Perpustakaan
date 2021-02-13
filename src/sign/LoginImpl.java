/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sign;


import repository.IUserRepository;

/**
 *
 * @author MATRIX COMPUTER
 */
public class LoginImpl implements LoginInterface {

    private final IUserRepository repo;

    public LoginImpl(IUserRepository repo) {
        this.repo = repo;
    }

    @Override
    public void login(String username, String password) {
       repo.login(username, password);
    }
}