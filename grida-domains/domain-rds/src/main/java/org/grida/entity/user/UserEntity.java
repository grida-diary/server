package org.grida.entity.user;

import lombok.*;
import org.grida.domain.user.Gender;
import org.grida.domain.user.UserAppearance;
import org.grida.domain.user.UserRole;
import org.grida.entity.base.BaseEntity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "users", indexes = {@Index(name = "idx_email", columnList = "email", unique = true)})
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

    @Column
    private String hairStyle;

    @Column
    private String glasses;

    @Builder
    public UserEntity(
            LocalDateTime createdAt,
            LocalDateTime lastModifiedAt,
            String email, String password,
            UserRole role,
            String nickname
    ) {
        super(createdAt, lastModifiedAt);
        this.email = email;
        this.password = password;
        this.role = role;
        this.nickname = nickname;
    }

    public void modifyAppearance(UserAppearance appearance) {
        this.age = appearance.age();
        this.gender = appearance.gender();
        this.hairStyle = appearance.hairStyle();
        this.glasses = appearance.glasses();
    }
}
