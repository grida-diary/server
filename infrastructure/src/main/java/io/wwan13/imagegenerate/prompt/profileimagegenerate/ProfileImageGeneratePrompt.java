package io.wwan13.imagegenerate.prompt.profileimagegenerate;

import io.wwan13.imagegenerate.config.PromptProperties;
import io.wwan13.imagegenerate.prompt.Prompt;
import org.springframework.stereotype.Component;

@Component
public class ProfileImageGeneratePrompt extends Prompt {

    public ProfileImageGeneratePrompt(PromptProperties properties) {
        super(properties.getProfileImageGenerate());
    }
}
