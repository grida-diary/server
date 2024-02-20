package org.grida.entity.user;

import org.grida.domain.core.DefaultDateTime;
import org.grida.domain.user.*;

import java.time.LocalDateTime;

public class UserMapper {

    public static User toUser(UserEntity entity) {
        return new User(
                entity.getId(),
                new UserAccount(entity.getRole(), entity.getEmail(), entity.getPassword(), entity.getNickname()),
                new UserAppearance(entity.getAge(), entity.getGender(), entity.getHairStyle(), entity.getGlasses()),
                new DefaultDateTime(entity.getCreatedAt(), entity.getLastModifiedAt())
        );
    }

    public static UserEntity toUserEntity(
            UserAccount account,
            LocalDateTime createdAt
    ) {
        return UserEntity.builder()
                .role(account.role())
                .email(account.email())
                .password(account.password())
                .nickname(account.nickname())
                .createdAt(createdAt)
                .lastModifiedAt(createdAt)
                .build();
    }
}
