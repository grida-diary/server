package io.wwan13.openai.image;

import io.wwan13.openai.config.OpenAiHeaderConfig;
import io.wwan13.openai.image.dto.ImageGenerateRequestDto;
import io.wwan13.openai.image.dto.ImageGenerateResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(
        name = "OpenAiImageGeneration",
        url = "https://api.openai.com/v1/images/generations",
        configuration = {OpenAiHeaderConfig.class})
public interface OpenAiImageClient {
    @PostMapping
    ImageGenerateResponseDto generateImage(ImageGenerateRequestDto imageGenerateRequestDto);
}
