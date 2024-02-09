package org.grida.domain.user;

import org.grida.domain.core.Target;

import java.time.LocalDateTime;

public interface UserRepository {

    Long save(UserAccount account, UserRole role, UserProfile profile, LocalDateTime lastActionAt);
    User find(Target target);
    void delete(Target target);
    boolean existByEmail(String email);
    UserAccount findAccountByEmail(String email);
    UserRole findRole(Target target);
}
