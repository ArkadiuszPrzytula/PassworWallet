package com.przytula.passwort_wallet.encoder;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PasswordEncoderFactory {
    public PasswordEncoder getPasswordEncoder(String passwordEncoder) {
        if (passwordEncoder.equals("bcrypt")) {
            return new BCryptPasswordEncoder();
        }
        if (passwordEncoder.equals("hmac")) {
            return new HmacPasswordEncoder();
        }
        return null;
    }
}
