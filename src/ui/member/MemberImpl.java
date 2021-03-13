/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.member;

import model.User;

import java.util.List;
import repository.IQueryRepository;

/**
 * @author MATRIX COMPUTER
 */
public class MemberImpl implements MemberInterface {

    private final IQueryRepository<User> userRepository;

    public MemberImpl(IQueryRepository<User> userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> read() {
        return userRepository.readAll();
    }

    @Override
    public boolean update(User user, int oldNim) {
        return userRepository.update(user, oldNim);
    }

    @Override
    public boolean delete(int nim) {
        return userRepository.delete(nim);
    }

    @Override
    public boolean create(User user) {
        return userRepository.create(user);
    }

    @Override
    public User search(int nim) {
        User user = userRepository.search(nim);
        return user;
    }

}