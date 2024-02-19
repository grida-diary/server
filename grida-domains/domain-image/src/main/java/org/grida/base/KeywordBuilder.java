package org.grida.base;

import org.grida.diaryimage.DiaryImageGenerateKeywordBuilder;
import org.grida.diaryimage.EmotionAnalysisKeywordBuilder;
import org.grida.exception.DomainImageException;
import org.grida.profileimage.ProfileImageGenerateKeywordBuilder;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static org.grida.exception.DomainImageErrorCode.KEYWORD_CANNOT_BE_EMPTY;

public abstract class KeywordBuilder {

    private final Map<String, String> keywords;

    protected KeywordBuilder() {
        this.keywords = new HashMap<>();
    }

    protected void addKeyword(String key, String value) {
        keywords.put(key, value);
    }

    public Keywords build() {
        validateIsAllFilled();
        return new Keywords(keywords);
    }

    private void validateIsAllFilled() {
        if (!allKeys().equals(keywords.keySet())) {
            throw new DomainImageException(KEYWORD_CANNOT_BE_EMPTY);
        }
    }

    protected abstract Set<String> allKeys();

    public static EmotionAnalysisKeywordBuilder emotionAnalysis() {
        return new EmotionAnalysisKeywordBuilder();
    }

    public static DiaryImageGenerateKeywordBuilder diaryImageGenerate() {
        return new DiaryImageGenerateKeywordBuilder();
    }

    public static ProfileImageGenerateKeywordBuilder profileImageGenerate() {
        return new ProfileImageGenerateKeywordBuilder();
    }
}
