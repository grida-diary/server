package io.wwan13.imagegenerate.processor.openai.client.image.dto;

import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ImageGenerateRequestDto {

    private String model;
    private String prompt;
    private Integer n;
    private String size;

    @Builder
    public ImageGenerateRequestDto(String model, String prompt, Integer n, String size) {
        this.model = model;
        this.prompt = prompt;
        this.n = n;
        this.size = size;
    }
}
