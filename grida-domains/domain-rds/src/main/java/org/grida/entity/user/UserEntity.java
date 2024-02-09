package org.grida.entity.user;

import lombok.*;
import org.grida.domain.user.Gender;
import org.grida.domain.user.UserRole;
import org.grida.entity.base.BaseEntity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class UserEntity extends BaseEntity {

    @Column
    private String email;

    @Column(name = "encrypted_password")
    private String password;

    @Column
    @Enumerated(EnumType.STRING)
    private UserRole role;

    @Column
    private String nickname;

    @Column
    private Integer age;

    @Column
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Builder
    public UserEntity(
            LocalDateTime createdAt,
            LocalDateTime lastModifiedAt,
            String email, String password,
            UserRole role,
            String nickname,
            Integer age,
            Gender gender
    ) {
        super(createdAt, lastModifiedAt);
        this.email = email;
        this.password = password;
        this.role = role;
        this.nickname = nickname;
        this.age = age;
        this.gender = gender;
    }
}
