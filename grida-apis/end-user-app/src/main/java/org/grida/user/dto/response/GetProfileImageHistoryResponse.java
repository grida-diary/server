package org.grida.user.dto.response;

import org.grida.CdnClient;
import org.grida.domain.user.ProfileImage;

import java.time.LocalDateTime;
import java.util.List;

public record GetProfileImageHistoryResponse(
        List<HistoryElement> images
) {

    public record HistoryElement(
            long imageId,
            String imageUrl,
            LocalDateTime createdAt,
            boolean isActivate
    ) {
    }

    public static GetProfileImageHistoryResponse of(List<ProfileImage> images, CdnClient cdnClient) {
        List<HistoryElement> elements = images.stream()
                .map(image ->
                        new HistoryElement(
                                image.id(),
                                cdnClient.getUrl(image.imageDetail().imagePath()),
                                image.dateTime().createdAt(),
                                image.imageDetail().isActivate()
                        )
                )
                .toList();

        return new GetProfileImageHistoryResponse(elements);
    }
}
