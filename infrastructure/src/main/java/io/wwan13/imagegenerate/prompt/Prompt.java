package io.wwan13.imagegenerate.prompt;

import io.wwan13.imagegenerate.exception.RegexNotContainsException;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@RequiredArgsConstructor
public abstract class Prompt {

    private static final String EMPTY_SPACE = " ";
    private static final String REGEX_PREFIX = "#";

    private final String prompt;

    public String create(Keywords keywords) {
        String result = prompt;

        for (String regex : keywords.getRegexes()) {
            validateRegexContains(regex);
            result = result.replaceAll(regex, keywords.getKeyword(regex));
        }

        return result;
    }

    private void validateRegexContains(String regex) {
        boolean containsExactly = Arrays.stream(prompt.split(EMPTY_SPACE))
                .filter(st -> st.startsWith(REGEX_PREFIX))
                .anyMatch(st -> st.equals(regex));

        if (!containsExactly) {
            throw new RegexNotContainsException();
        }
    }
}
