package org.grida.client.image;

import org.grida.client.image.dto.ImageRequestDto;
import org.grida.client.image.dto.ImageResponseDto;
import org.grida.config.ClientHeaderConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(
        name = "OpenAiImageGeneration",
        url = "https://api.openai.com/v1/images/generations",
        configuration = {ClientHeaderConfig.class})
public interface OpenAiImageClient {
    @PostMapping
    ImageResponseDto generateImage(ImageRequestDto imageGenerateRequestDto);
}