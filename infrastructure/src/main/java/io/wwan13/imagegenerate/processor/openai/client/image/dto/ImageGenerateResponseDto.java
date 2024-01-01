package io.wwan13.imagegenerate.processor.openai.client.image.dto;

import io.wwan13.imagegenerate.processor.image.ImageProcessResult;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class ImageGenerateResponseDto {

    private List<ImageUrl> data;

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor
    public static class ImageUrl {
        private String url;
    }

    public ImageProcessResult toProcessResult() {
        return new ImageProcessResult(
                data.stream()
                        .map(imageUrl -> imageUrl.url)
                        .collect(Collectors.toList())
        );
    }
}
