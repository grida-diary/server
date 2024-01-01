package io.wwan13.imagegenerate.processor.openai.processor;

import io.wwan13.imagegenerate.exception.InvalidNumberOfImagesException;
import io.wwan13.imagegenerate.processor.openai.client.image.OpenAiImageClient;
import io.wwan13.imagegenerate.processor.openai.client.image.dto.ImageGenerateRequestDto;
import io.wwan13.imagegenerate.processor.openai.client.image.dto.ImageGenerateResponseDto;
import io.wwan13.imagegenerate.processor.image.ImageProcessResult;
import io.wwan13.imagegenerate.processor.image.ImageProcessor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class OpenAiImageProcessor implements ImageProcessor {

    private static final String IMAGE_SIZE = "1024x1024";
    private static final int NUMBER_OF_IMAGES_MIN_VALUE = 1;

    private final OpenAiImageClient imageClient;
    private final String model;

    public OpenAiImageProcessor(OpenAiImageClient imageClient,
                                @Value("${open-ai.image.model}") String model) {
        this.imageClient = imageClient;
        this.model = model;
    }

    @Override
    public ImageProcessResult proceed(String prompt, int n) {
        validateNumberOfImages(n);

        ImageGenerateRequestDto request = ImageGenerateRequestDto.builder()
                .model(model)
                .prompt(prompt)
                .n(n)
                .size(IMAGE_SIZE)
                .build();

        ImageGenerateResponseDto response = imageClient.generateImage(request);
        return response.toProcessResult();
    }

    private void validateNumberOfImages(int n) {
        if (n < NUMBER_OF_IMAGES_MIN_VALUE) {
            throw new InvalidNumberOfImagesException();
        }
    }
}
