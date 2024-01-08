package io.wwan13.imagegenerate.prompt.naturallanguageprocess;

import io.wwan13.imagegenerate.config.PromptProperties;
import io.wwan13.imagegenerate.prompt.Prompt;
import org.springframework.stereotype.Component;

@Component
public class NaturalLanguageProcessPrompt extends Prompt {

    public NaturalLanguageProcessPrompt(PromptProperties properties) {
        super(properties.getNaturalLanguageProcess());
    }
}
