package com.example.online_shop.utils;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.stereotype.Component;

@Component
public class ArgonUtil {

    private ArgonUtil(){}

    private static final Argon2 ARGON_2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);

    public static String hashPassword(CharSequence rawPassword) {
        return ARGON_2.hash(4, 12000, 8, rawPassword.toString());
    }

    public static Boolean matches(CharSequence rawPassword, String encodedPassword) {
        return ARGON_2.verify(encodedPassword, rawPassword.toString());
    }
}
