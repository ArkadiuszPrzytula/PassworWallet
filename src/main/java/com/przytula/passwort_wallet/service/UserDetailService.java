package com.przytula.passwort_wallet.service;

import com.przytula.passwort_wallet.entity.User;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserDetailService implements UserDetailsService {

    private UserService userService;

    @Autowired
    public UserDetailService(UserService userService) {
        this.userService = userService;
    }


    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User userByLogin = userService.findByLogin(login);
        return new org.springframework.security.core.userdetails.User(
                userByLogin.getLogin(),
                userByLogin.getPassword(),
                Collections.singleton(new SimpleGrantedAuthority("Role_User")));
    }
}
