package org.grida.domain.user;

import org.grida.domain.core.DefaultDateTime;

import java.time.LocalDateTime;

public class StubUserRepository implements UserRepository {

    public static final long USER_ID = 1L;
    public static final String USER_EMAIL = "existEmail@gmail.com";
    public static final String USER_PASSWORD = "password";
    public static final UserRole USER_ROLE = UserRole.ROLE_BASIC_USER;
    public static final String USER_NICKNAME = "nickname";
    public static final int USER_AGE = 20;
    public static final Gender USER_GENDER = Gender.MAN;
    public static final String USER_HAIRSTYLE = "permed long black";
    public static final String USER_GLASSES = "no glasses";
    public static final String NON_EXIST_USER_EMAIL = "nonExistEmail@gmail.com";

    @Override
    public String saveAccount(UserAccount account, LocalDateTime lastActionAt) {
        return account.email();
    }

    @Override
    public User findByEmail(String email) {
        return new User(
                USER_ID,
                new UserAccount(USER_ROLE, email, USER_PASSWORD, USER_NICKNAME),
                new UserAppearance(USER_AGE, USER_GENDER, USER_HAIRSTYLE, USER_GLASSES),
                new DefaultDateTime(LocalDateTime.MIN, LocalDateTime.MIN)
        );
    }

    @Override
    public boolean existByEmail(String email) {
        return email.equals(USER_EMAIL);
    }

    @Override
    public UserAccount findAccountByEmail(String email) {
        return new UserAccount(USER_ROLE, email, USER_PASSWORD, USER_NICKNAME);
    }

    @Override
    public UserAppearance findAppearanceByEmail(String email) {
        return null;
    }

    @Override
    public UserRole findRoleByEmail(String provider) {
        return USER_ROLE;
    }

    @Override
    public String modifyAppearance(String email, UserAppearance appearance) {
        return null;
    }
}
