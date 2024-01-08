package io.wwan13.imagegenerate.prompt.diaryimagegenerate;

import io.wwan13.imagegenerate.config.PromptProperties;
import io.wwan13.imagegenerate.prompt.Prompt;
import org.springframework.stereotype.Component;

@Component
public class DiaryImageGeneratePrompt extends Prompt {

    public DiaryImageGeneratePrompt(PromptProperties properties) {
        super(properties.getDiaryImageGenerate());
    }
}
