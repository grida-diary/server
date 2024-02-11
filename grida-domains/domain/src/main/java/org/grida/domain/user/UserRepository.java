package org.grida.domain.user;

import java.time.LocalDateTime;

public interface UserRepository {

    String save(UserAccount account, UserProfile profile, LocalDateTime lastActionAt);
    User findByEmail(String email);
    boolean existByEmail(String email);
    UserAccount findAccountByEmail(String email);
    UserRole findRoleByEmail(String email);
}
