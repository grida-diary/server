package io.wwan13.domain.member.entity.wrap;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Authority {
    ROLE_USER(1),
    ROLE_PRO_USER(5),
    ROLE_ADMIN(99);

    private final Integer refreshChance;
}
