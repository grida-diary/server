package io.wwan13.imagegenerate.prompt.naturallanguageprocess;

import io.wwan13.imagegenerate.exception.KeywordCannotBeEmptyException;
import io.wwan13.imagegenerate.prompt.Keywords;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

public class NaturalLanguageProcessKeywords extends Keywords {

    private static final String DIARY_REGEX = "#DIARY";

    private NaturalLanguageProcessKeywords(Map<String, String> keywords) {
        super(keywords);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private String diary;

        public Builder diary(String diary) {
            this.diary = diary;
            return this;
        }

        public NaturalLanguageProcessKeywords build() {
            validateIsFilled();
            Map<String, String> keywords = new HashMap<>();
            keywords.put(DIARY_REGEX, diary);

            return new NaturalLanguageProcessKeywords(keywords);
        }

        private void validateIsFilled() {
            if (!StringUtils.hasText(diary)) {
                throw new KeywordCannotBeEmptyException();
            }
        }
    }
}
