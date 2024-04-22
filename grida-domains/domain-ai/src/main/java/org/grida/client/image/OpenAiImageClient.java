package org.grida.client.image;

import org.grida.client.image.dto.ImageGenerateRequest;
import org.grida.client.image.dto.ImageResponse;
import org.grida.config.ClientHeaderConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(
        name = "OpenAiImageGeneration",
        url = "https://api.openai.com/v1/images/generations",
        configuration = {ClientHeaderConfig.class}
)
public interface OpenAiImageClient {
    @PostMapping("/generations")
    ImageResponse generateImage(ImageGenerateRequest imageGenerateRequest);

    @PostMapping("/edits")
    ImageResponse editImage(MultiValueMap<String, Object> imageEditRequest);
}