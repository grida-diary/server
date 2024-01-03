package io.wwan13.imagegenerate.prompt.diaryimagegenerate;

import io.wwan13.imagegenerate.prompt.Prompt;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class DiaryImageGeneratePrompt extends Prompt {

    public DiaryImageGeneratePrompt(
            @Value("${prompt.diary-image-create}") String prompt) {
        super(prompt);
    }
}
