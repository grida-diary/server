package io.wwan13.imagegenerate.prompt.naturallanguageprocess;

import io.wwan13.imagegenerate.prompt.Prompt;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class NaturalLanguageProcessPrompt extends Prompt {

    public NaturalLanguageProcessPrompt(
            @Value("${prompt.natural-language-process}") String prompt) {
        super(prompt);
    }
}
