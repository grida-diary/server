package org.grida.client.image.dto;

import org.grida.processor.imagegenerate.ImageGenerateResult;

import java.util.List;

public record ImageResponse(
        List<ImageUrl> data
) {

    public record ImageUrl(
            String url
    ) {
    }

    public ImageGenerateResult toResult() {
        return new ImageGenerateResult(
                data.stream()
                        .map(imageUrl -> imageUrl.url)
                        .toList()
        );
    }
}
