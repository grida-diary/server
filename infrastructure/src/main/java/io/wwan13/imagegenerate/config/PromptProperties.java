package io.wwan13.imagegenerate.config;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@Getter
@RequiredArgsConstructor
@ConfigurationProperties(prefix = "image-generate.prompt")
@ConstructorBinding
public class PromptProperties {

    private final String naturalLanguageProcess;
    private final String diaryImageGenerate;
    private final String profileImageGenerate;
}
