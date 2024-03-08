package org.grida.entity.profileimage;

import org.grida.domain.core.DefaultDateTime;
import org.grida.domain.core.ImageDetail;
import org.grida.domain.user.ProfileImage;

import java.time.LocalDateTime;

public class ProfileImageMapper {

    private ProfileImageMapper() {
    }

    public static ProfileImage toProfileImage(ProfileImageEntity entity) {
        return new ProfileImage(
                entity.getId(),
                entity.getUserEmail(),
                new ImageDetail(entity.getImagePath(), entity.getIsActivate()),
                new DefaultDateTime(entity.getCreatedAt(), entity.getLastModifiedAt())
        );
    }

    public static ProfileImageEntity toProfileImageEntity(
            String userEmail,
            ImageDetail imageDetail,
            LocalDateTime lastActionAt
    ) {
        return ProfileImageEntity.builder()
                .userEmail(userEmail)
                .imagePath(imageDetail.imagePath())
                .isActivate(imageDetail.isActivate())
                .createdAt(lastActionAt)
                .lastModifiedAt(lastActionAt)
                .build();
    }
}
