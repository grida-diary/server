package io.wwan13.imagegenerate.prompt;

import io.wwan13.imagegenerate.exception.InvalidPrefixRegexException;
import io.wwan13.imagegenerate.exception.InvalidSizeRegexException;
import io.wwan13.imagegenerate.exception.RegexCannotBeLowerCaseException;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public abstract class Keywords {

    private static final String REGEX_PREFIX = "#";
    private static final int REGEX_MIN_SIZE = 2;

    private final Map<String, String> keywords;

    public Keywords(Map<String, String> keywords) {
        validateRegexPrefix(keywords.keySet());
        validateRegexIsUpperCase(keywords.keySet());
        validateRegexSize(keywords.keySet());
        this.keywords = new HashMap<>(keywords);
    }

    private void validateRegexPrefix(Set<String> regexes) {
        boolean hasInvalidPrefix = regexes.stream()
                .anyMatch(regex -> !regex.startsWith(REGEX_PREFIX));

        if (hasInvalidPrefix) {
            throw new InvalidPrefixRegexException();
        }
    }

    private void validateRegexIsUpperCase(Set<String> regexes) {
        boolean hasLowerCase = regexes.stream()
                .anyMatch(regex -> {
                    String upperRegex = regex.toUpperCase();
                    return !upperRegex.equals(regex);
                });

        if (hasLowerCase) {
            throw new RegexCannotBeLowerCaseException();
        }
    }

    private void validateRegexSize(Set<String> regexes) {
        boolean hasInvalidSizedRegex = regexes.stream()
                .anyMatch(regex -> regex.length() < REGEX_MIN_SIZE);

        if (hasInvalidSizedRegex) {
            throw new InvalidSizeRegexException();
        }
    }

    public Set<String> getRegexes() {
        return Collections.unmodifiableSet(keywords.keySet());
    }

    public String getKeyword(String regex) {
        return keywords.get(regex);
    }
}
