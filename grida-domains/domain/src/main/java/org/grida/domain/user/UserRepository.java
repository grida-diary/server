package org.grida.domain.user;

import java.time.LocalDateTime;

public interface UserRepository {

    String saveAccount(UserAccount account, LocalDateTime lastActionAt);
    User findByEmail(String email);
    boolean existByEmail(String email);
    UserAccount findAccountByEmail(String email);
    UserAppearance findAppearanceByEmail(String email);
    UserRole findRoleByEmail(String email);
    String modifyAppearance(String email, UserAppearance appearance);
}
