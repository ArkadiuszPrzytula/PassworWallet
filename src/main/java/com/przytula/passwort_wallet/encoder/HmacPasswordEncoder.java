package com.przytula.passwort_wallet.encoder;

import com.sun.xml.bind.api.impl.NameConverter;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class HmacPasswordEncoder implements PasswordEncoder {
    private static final String HMAC_KEY = "Easter egg";
    private static final String HMAC_SHA512 = "HmacSHA512";

    @Override
    public String encode(CharSequence rawPassword) {
        return calculate((String) rawPassword, HMAC_KEY);
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return encode(rawPassword).equals(encodedPassword);
    }

    private String calculate(String rawPassword, String hmacKey) {
        Mac sha512Hmac;
        String result = "";
        try {
            final byte[] secretKeySpec = hmacKey.getBytes(StandardCharsets.UTF_8);
            sha512Hmac = Mac.getInstance(hmacKey);
            SecretKeySpec keySpec = new SecretKeySpec(secretKeySpec, hmacKey);
            sha512Hmac.init(keySpec);
            byte[] macData = sha512Hmac.doFinal(rawPassword.getBytes(StandardCharsets.UTF_8));
            result = Base64.getEncoder().encodeToString(macData);

        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            e.printStackTrace();
        }
        return result;
    }


}
