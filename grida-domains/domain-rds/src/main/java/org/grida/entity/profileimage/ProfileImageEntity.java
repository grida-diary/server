package org.grida.entity.profileimage;

import lombok.*;
import org.grida.entity.base.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "profile_image", indexes = {@Index(name = "idx_user_email", columnList = "userEmail")})
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class ProfileImageEntity extends BaseEntity {

    @Column
    private String userEmail;

    @Column
    private String imagePath;

    @Column
    private Boolean isActivate;

    @Builder
    public ProfileImageEntity(
            LocalDateTime createdAt,
            LocalDateTime lastModifiedAt,
            String userEmail,
            String imagePath,
            boolean isActivate
    ) {
        super(createdAt, lastModifiedAt);
        this.userEmail = userEmail;
        this.imagePath = imagePath;
        this.isActivate = isActivate;
    }

    public void deactivate(LocalDateTime lastModifiedAt) {
        this.isActivate = false;
        this.lastModifiedAt = lastModifiedAt;
    }

    public void activate(LocalDateTime lastModifiedAt) {
        this.isActivate = true;
        this.lastModifiedAt = lastModifiedAt;
    }
}
