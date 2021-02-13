package repository;

import model.User;

import java.util.List;

public interface IUserRepository extends IRepository<User>{
     List<User> showAllUser();
     User searchUser(int nim);

     void login(String username, String password);
}
