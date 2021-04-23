/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sign;


import model.Admin;
import model.User;
import repository.IQueryRepository;
import repository.UserRepository;
import repository.local.MySqlConnection;

/**
 *
 * @author MATRIX COMPUTER
 */
public class LoginImpl implements LoginInterface {
    private LoginCallback callback;
    private final IQueryRepository<User> repo = 
            new UserRepository(new MySqlConnection());

    public LoginImpl(LoginCallback callback) {
        this.callback = callback;
    }

    @Override
    public void login(String username, String password) {
       callback.hasLogin(repo.login(username, password));
    }
}