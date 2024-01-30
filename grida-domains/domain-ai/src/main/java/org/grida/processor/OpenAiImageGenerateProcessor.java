package org.grida.processor;

import lombok.RequiredArgsConstructor;
import org.grida.client.image.OpenAiImageClient;
import org.grida.client.image.dto.ImageRequestDto;
import org.grida.client.image.dto.ImageResponseDto;
import org.grida.config.OpenAiProperties;
import org.grida.exception.DomainAiException;
import org.grida.processor.imagegenerate.ImageGenerateProcessor;
import org.grida.processor.imagegenerate.ImageGenerateResult;

import static org.grida.exception.DomainAiErrorCode.INVALID_IMAGE_COUNT;

@RequiredArgsConstructor
public class OpenAiImageGenerateProcessor implements ImageGenerateProcessor {

    private static final String IMAGE_SIZE = "1024x1024";
    private static final int MIN_IMAGE_COUNT = 1;

    private final OpenAiProperties properties;
    private final OpenAiImageClient imageClient;

    @Override
    public ImageGenerateResult proceed(String prompt, int n) {
        validateNumberOfImages(n);

        ImageRequestDto request = creatRequest(prompt, n);
        ImageResponseDto response = imageClient.generateImage(request);
        return response.toResult();
    }

    private void validateNumberOfImages(int n) {
        if (n < MIN_IMAGE_COUNT) {
            throw new DomainAiException(INVALID_IMAGE_COUNT, MIN_IMAGE_COUNT);
        }
    }

    private ImageRequestDto creatRequest(String prompt, int n) {
        return ImageRequestDto.builder()
                .model(properties.getImageModel())
                .prompt(prompt)
                .n(n)
                .size(IMAGE_SIZE)
                .build();
    }
}
