package org.chtijbug.drools.console.security;

import org.springframework.security.crypto.password.PasswordEncoder;

public final class MyNoOpPasswordEncoder implements PasswordEncoder {

    public String encode(CharSequence rawPassword) {
        return rawPassword.toString();
    }

    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return rawPassword.toString().equals(encodedPassword);
    }

    /**
     * Get the singleton {@link org.springframework.security.crypto.password.NoOpPasswordEncoder}.
     */
    public static PasswordEncoder getInstance() {
        return INSTANCE;
    }

    private static final PasswordEncoder INSTANCE = new MyNoOpPasswordEncoder();

    public MyNoOpPasswordEncoder() {
    }

}
