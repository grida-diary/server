package org.grida.user.usecase;

import lombok.RequiredArgsConstructor;
import org.grida.domain.user.ProfileImageService;
import org.grida.dto.IdResponse;
import org.grida.user.dto.request.ChangeActivateImageRequest;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ChangeActivateImageUseCase {

    private final ProfileImageService profileImageService;

    public IdResponse execute(String userEmail, ChangeActivateImageRequest request) {
        long id = profileImageService.changeActivateImage(userEmail, request.targetImageId());
        return new IdResponse(id);
    }
}
