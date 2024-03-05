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

import static org.grida.exception.EndUserErrorCode.ALREADY_COMPLETE_ONBOARDING;

@Component
@RequiredArgsConstructor
public class OnboardingUseCase {

    private final UserService userService;
    private final UserWithImageService userWithImageService;
    private final ProfileImageGenerator profileImageGenerator;
    private final StorageClient storageClient;

    public UserEmailResponse execute(String userEmail, ProfileImageRequest request) {
        validateAlreadyCompleteOnboarding(userEmail);

        ImageMetaData generatedImage = profileImageGenerator.generate(request.toImageAppearance());
        String email = userWithImageService
                .onboarding(userEmail, request.toUserAppearance(), generatedImage.toImagePath());
        uploadToStorage(generatedImage);

        return new UserEmailResponse(email);
    }

    private void validateAlreadyCompleteOnboarding(String userEmail) {
        if (!userService.needOnboarding(userEmail)) {
            throw new EndUserAppException(ALREADY_COMPLETE_ONBOARDING);
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
