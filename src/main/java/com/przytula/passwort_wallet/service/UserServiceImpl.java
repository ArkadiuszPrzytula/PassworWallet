package com.przytula.passwort_wallet.service;

import com.przytula.passwort_wallet.encoder.AesEncryptor;
import com.przytula.passwort_wallet.encoder.PasswordEncoderFactory;
import com.przytula.passwort_wallet.entity.Credential;
import com.przytula.passwort_wallet.entity.User;
import com.przytula.passwort_wallet.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.security.crypto.password.PasswordEncoder;

public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;

    private  final PasswordEncoderFactory passwordEncoderFactory;

    @Autowired
    public UserServiceImpl(UserRepo userRepo, PasswordEncoderFactory passwordEncoderFactory) {
        this.userRepo = userRepo;
        this.passwordEncoderFactory = passwordEncoderFactory;
    }

    @Override
    public User findByLogin(String login) {
        return userRepo.findByLogin(login).orElseThrow(() -> new UsernameNotFoundException("Cannot find user with login: " + login));
    }

    @Override
    public void save(User user) {
        userRepo.save(user);
    }

    @Override
    public void saveUserWithPasswordEncoder(User user, String passwordEncoder) {
        PasswordEncoder encoder = passwordEncoderFactory.getPasswordEncoder(passwordEncoder);
        user.setPassword("{"+passwordEncoder+"}" + encoder.encode(user.getPassword()));

        if (user.getUserCredentials() !=null) {
            for (Credential userCredential: user.getUserCredentials()){
                userCredential.setWebPassword(new AesEncryptor().encrypt(userCredential.getWebPassword(), user.getPassword()));
            }
        }


    }
}
