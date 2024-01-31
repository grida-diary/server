package org.grida.config;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@Getter
@RequiredArgsConstructor
@ConstructorBinding
@ConfigurationProperties(prefix = "prompt")
public class PromptProperties {

    private final String emotionAnalysis;
    private final String diaryImageGenerate;
    private final String profileImageGenerate;
}
