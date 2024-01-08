package io.wwan13.imagegenerate.config;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@Getter
@RequiredArgsConstructor
@ConfigurationProperties(prefix = "image-generate.open-ai")
@ConstructorBinding
public class OpenAiProperties {

    private final String secretKey;
    private final String imageModel;
    private final String chatModel;
    private final String chatRole;
}
