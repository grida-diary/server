package io.wwan13.auth.usecase;

import io.wwan13.annotation.UseCase;
import io.wwan13.domain.image.entity.Image;
import io.wwan13.domain.image.service.ImageService;
import io.wwan13.domain.profileimage.entity.ProfileImage;
import io.wwan13.domain.profileimage.service.ProfileImageService;
import io.wwan13.auth.dto.CreateProfileImageResponseDto;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class CreateProfileImageUseCase {

    private final ProfileImageService profileImageService;
    private final ImageService imageService;

    public CreateProfileImageResponseDto execute(String memberEmail, String profileImageUrl) {
        Image image = imageService.save(new Image(profileImageUrl));
        ProfileImage profileImage = profileImageService.save(memberEmail, image.getId());

        return new CreateProfileImageResponseDto(profileImage.getId());
    }
}
