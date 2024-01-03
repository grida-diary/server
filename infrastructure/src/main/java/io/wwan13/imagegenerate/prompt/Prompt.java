package io.wwan13.imagegenerate.prompt;

import io.wwan13.imagegenerate.exception.PromptKeywordNotMatchesException;
import io.wwan13.imagegenerate.exception.RegexNotContainsException;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;


@RequiredArgsConstructor
public abstract class Prompt {

    private static final String EMPTY_SPACE = " ";
    private static final String REGEX_PREFIX = "#";
    private static final String PROMPT_CLASS_SUFFIX = "Prompt";
    private static final String KEYWORD_CLASS_SUFFIX = "Keywords";

    private final String prompt;

    public String create(Keywords keywords) {
        validatePromptKeywordsMatches(keywords);
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

    private void validatePromptKeywordsMatches(Keywords keywords) {
        String promptClassName = this.getClass().getSimpleName();
        String keywordClassName = keywords.getClass().getSimpleName();

        String promptPrefix = promptClassName.replace(PROMPT_CLASS_SUFFIX, EMPTY_SPACE);
        String keywordPrefix = keywordClassName.replace(KEYWORD_CLASS_SUFFIX, EMPTY_SPACE);

        if (!promptPrefix.equals(keywordPrefix)) {
            throw new PromptKeywordNotMatchesException();
        }
    }
}
