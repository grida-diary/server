package org.grida.user.usecase;

import lombok.RequiredArgsConstructor;
import org.grida.CdnClient;
import org.grida.domain.user.*;
import org.grida.user.dto.response.GetActivateImageResponse;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GetActivateImageUseCase {

    private final UserService userService;
    private final ProfileImageService profileImageService;
    private final UserWithImageService userWithImageService;
    private final CdnClient cdnClient;

    public GetActivateImageResponse execute(String userEmail) {
        return GetActivateImageResponse.of(
                userService.readAppearance(userEmail),
                cdnClient.getUrl(profileImageService.readActivateImagePath(userEmail)),
                userWithImageService.canRefreshProfileImage(userEmail)
        );
    }
}
