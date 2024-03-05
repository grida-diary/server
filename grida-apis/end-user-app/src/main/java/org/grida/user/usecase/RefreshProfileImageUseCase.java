package org.grida.user.usecase;

import lombok.RequiredArgsConstructor;
import org.grida.StorageClient;
import org.grida.base.ImageMetaData;
import org.grida.domain.user.UserService;
import org.grida.domain.user.UserWithImageService;
import org.grida.dto.UserEmailResponse;
import org.grida.exception.EndUserAppException;
import org.grida.model.FileData;
import org.grida.profileimage.ProfileImageGenerator;
import org.grida.user.dto.request.ProfileImageRequest;
import org.springframework.stereotype.Component;

import static org.grida.exception.EndUserErrorCode.DO_ONBOARDING_FIRST;

@Component
@RequiredArgsConstructor
public class RefreshProfileImageUseCase {

    private final UserService userService;
    private final UserWithImageService userWithImageService;
    private final ProfileImageGenerator profileImageGenerator;
    private final StorageClient storageClient;

    public UserEmailResponse execute(String userEmail, ProfileImageRequest request) {
        validateDoOnboardingFirst(userEmail);

        ImageMetaData generatedImage = profileImageGenerator.generate(request.toImageAppearance());
        String email = userWithImageService
                .refreshProfileImage(userEmail, request.toUserAppearance(), generatedImage.toImagePath());
        uploadToStorage(generatedImage);

        return new UserEmailResponse(email);
    }

    private void validateDoOnboardingFirst(String userEmail) {
        if (userService.needOnboarding(userEmail)) {
            throw new EndUserAppException(DO_ONBOARDING_FIRST);
        }
    }

    private void uploadToStorage(ImageMetaData generatedImage) {
        FileData fileData = FileData.builder()
                .fileName(generatedImage.name())
                .directory(generatedImage.directory())
                .extension(generatedImage.extension())
                .build();
        storageClient.upload(generatedImage.url(), fileData);
    }
}
