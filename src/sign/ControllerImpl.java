/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sign;


import model.User;
import repository.IQueryRepository;
import repository.UserRepository;
import repository.local.MySqlConnection;

/**
 *
 * @author MATRIX COMPUTER
 */
public class ControllerImpl implements Controllers {
    private LoginInterface view;
    private final IQueryRepository<User> repo = 
            new UserRepository(new MySqlConnection());

    public void setView(Login view) {
        this.view = view;
    }

    @Override
    public void login(String username, String password) {
       view.onLogin(repo.login(username, password));
    }
}