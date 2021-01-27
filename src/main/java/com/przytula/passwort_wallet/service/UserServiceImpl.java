package com.przytula.passwort_wallet.service;

import com.przytula.passwort_wallet.entity.User;

public class UserServiceImpl implements UserService {
    @Override
    public User findByLogin(String login) {
        return null;
    }

    @Override
    public void save(User user) {

    }

    @Override
    public void saveUserWithPasswordEncoder(User user, String PasswordEncoder) {

    }
}
