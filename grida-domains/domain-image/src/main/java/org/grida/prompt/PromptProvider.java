package org.grida.prompt;

import org.grida.exception.DomainImageException;
import org.springframework.stereotype.Component;

import static org.grida.exception.DomainImageErrorCode.PROMPT_NOT_CONTAINS_KEY;

@Component
public class PromptProvider {

    public String provide(Prompt prompt, PromptKeywords promptKeywords) {
        String result = prompt.getSkeleton();

        for (String key : promptKeywords.getKeys()) {
            validatePromptContainsKey(prompt.getSkeleton(), key);
            result = result.replaceAll(key, promptKeywords.getValueByKey(key));
        }

        return result;

    }

    private void validatePromptContainsKey(String skeleton, String key) {
        if (!skeleton.contains(key)) {
            throw new DomainImageException(PROMPT_NOT_CONTAINS_KEY);
        }
    }
}
