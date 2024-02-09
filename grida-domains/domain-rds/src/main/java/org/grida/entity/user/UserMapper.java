package org.grida.entity.user;

import org.grida.domain.core.DefaultDateTime;
import org.grida.domain.user.*;

import java.time.LocalDateTime;

public class UserMapper {

    public static User toUser(UserEntity entity) {
        return new User(
                entity.getId(),
                entity.getRole(),
                new UserAccount(entity.getEmail(), entity.getPassword()),
                new UserProfile(entity.getNickname(), entity.getAge(), entity.getGender()),
                new DefaultDateTime(entity.getCreatedAt(), entity.getLastModifiedAt())
        );
    }

    public static UserEntity toUserEntity(
            UserAccount account,
            UserRole role,
            UserProfile profile,
            LocalDateTime createdAt
    ) {
        return UserEntity.builder()
                .role(role)
                .email(account.email())
                .password(account.password())
                .nickname(profile.nickname())
                .age(profile.age())
                .gender(profile.gender())
                .createdAt(createdAt)
                .lastModifiedAt(createdAt)
                .build();
    }
}
