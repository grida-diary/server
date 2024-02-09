package org.grida.domain.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UserRole {

    ROLE_SUPER_ADMIN(Integer.MAX_VALUE, 0),
    ROLE_ADMIN(Integer.MAX_VALUE, 0),
    ROLE_BASIC_USER(1, 30),
    ROLE_PREMIUM_USER(5, 7);

    private final int diaryImageRefreshChance;
    private final int profileImageRefreshTerm;
}
