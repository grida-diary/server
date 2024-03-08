package org.grida.entity.profileimage;

import org.grida.domain.core.ImageDetail;
import org.grida.domain.user.ProfileImage;
import org.grida.entity.base.BaseMapper;

import java.time.LocalDateTime;

public class ProfileImageMapper {

    private ProfileImageMapper() {
    }

    public static ProfileImage toProfileImage(ProfileImageEntity entity) {
        return new ProfileImage(
                entity.getId(),
                entity.getUserEmail(),
                new ImageDetail(entity.getImagePath(), entity.getIsActivate()),
                BaseMapper.toDefaultDateTime(entity)
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
