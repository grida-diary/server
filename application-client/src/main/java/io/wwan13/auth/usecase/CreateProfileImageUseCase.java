package io.wwan13.auth.usecase;

import io.wwan13.annotation.UseCase;
import io.wwan13.domain.profileimage.service.ProfileImageService;
import io.wwan13.auth.dto.CreateProfileImageResponseDto;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class CreateProfileImageUseCase {

    private final ProfileImageService profileImageService;

    public CreateProfileImageResponseDto execute(String memberEmail, String profileImageUrl) {
        Long createdProfileImageId = profileImageService.save(memberEmail, profileImageUrl);
        return new CreateProfileImageResponseDto(createdProfileImageId);
    }
}
