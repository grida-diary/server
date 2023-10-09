package io.wwan13.openai.image.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ImageGenerateRequestDto {

    private String prompt;
    private final Integer n = 1;
    private final String size = "1024x1024";

    private ImageGenerateRequestDto(String prompt) {
        this.prompt = prompt;
    }

    public static ImageGenerateRequestDto prompt(String prompt) {
        return new ImageGenerateRequestDto(prompt);
    }
}
