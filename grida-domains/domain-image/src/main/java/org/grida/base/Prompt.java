package org.grida.base;

import lombok.RequiredArgsConstructor;
import org.grida.exception.DomainImageException;

import static org.grida.exception.DomainImageErrorCode.PROMPT_NOT_CONTAINS_KEY;

@RequiredArgsConstructor
public abstract class Prompt {

    private final String prompt;

    public String create(Keywords keywords) {
        String result = prompt;

        for (String key : keywords.getKeys()) {
            validatePromptContainsKey(key);
            result = result.replaceAll(key, keywords.getValues(key));
        }

        return result;
    }

    private void validatePromptContainsKey(String key) {
        if (!prompt.contains(key)) {
            throw new DomainImageException(PROMPT_NOT_CONTAINS_KEY);
        }
    }
}
