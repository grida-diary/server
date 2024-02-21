package org.grida.user.usecase;

import lombok.RequiredArgsConstructor;
import org.grida.CdnClient;
import org.grida.domain.user.ProfileImage;
import org.grida.domain.user.ProfileImageService;
import org.grida.user.dto.response.GetProfileImageHistoryResponse;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class GetProfileImageHistoryUseCase {

    private final ProfileImageService profileImageService;
    private final CdnClient cdnClient;

    public GetProfileImageHistoryResponse execute(String userEmail) {
        List<ProfileImage> images = profileImageService.readImageHistory(userEmail);
        return GetProfileImageHistoryResponse.of(images, cdnClient);
    }
}
