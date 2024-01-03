package io.wwan13.imagegenerate.prompt.profileimagegenerate;

import io.wwan13.imagegenerate.prompt.Prompt;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ProfileImageGeneratePrompt extends Prompt {

    public ProfileImageGeneratePrompt(
            @Value("${prompt.profile-image-create}") String prompt) {
        super(prompt);
    }
}
