package io.wwan13.imagegenerate.processor.openai.client.image;

import io.wwan13.imagegenerate.processor.openai.client.image.dto.ImageGenerateRequestDto;
import io.wwan13.imagegenerate.processor.openai.client.image.dto.ImageGenerateResponseDto;
import io.wwan13.imagegenerate.processor.openai.config.OpenAiHeaderConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(
        name = "OpenAiImageGeneration",
        url = "https://api.openai.com/v1/images/generations",
        configuration = {OpenAiHeaderConfig.class})
public interface OpenAiImageClient {
    @PostMapping
    ImageGenerateResponseDto generateImage(ImageGenerateRequestDto imageGenerateRequestDto);
}
