package io.wwan13.openai.client.image.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ImageGenerateRequestDto {

    private String prompt;
    private Integer n;
    private final String size = "512x512";

    public ImageGenerateRequestDto(String prompt, Integer n) {
        this.prompt = prompt;
        this.n = n;
    }
}
