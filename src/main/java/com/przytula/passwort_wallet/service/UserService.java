package com.przytula.passwort_wallet.service;

import com.przytula.passwort_wallet.entity.User;

public interface UserService {
    User findByLogin(String login);
    void save(User user);
    void saveUserWithPasswordEncoder(User user, String PasswordEncoder);
}
