package io.wwan13.imagegenerate.prompt.diaryimagegenerate;

import io.wwan13.imagegenerate.exception.KeywordCannotBeEmptyException;
import io.wwan13.imagegenerate.prompt.Keywords;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

public class DiaryImageGenerateKeywords extends Keywords {

    private static final String GENDER_REGEX = "#GENDER";
    private static final String AGE_REGEX = "#AGE";
    private static final String EMOTION_REGEX = "#EMOTION";
    private static final String BEHAVIOR_REGEX = "#BEHAVIOR";

    private DiaryImageGenerateKeywords(Map<String, String> keywords) {
        super(keywords);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private String gender;
        private String age;
        private String emotion;
        private String behavior;

        public Builder gender(String gender) {
            this.gender = gender;
            return this;
        }

        public Builder age(int age) {
            this.age = Integer.toString(age);
            return this;
        }

        public Builder emotion(String emotion) {
            this.emotion = emotion;
            return this;
        }

        public Builder behavior(String behavior) {
            this.behavior = behavior;
            return this;
        }

        public DiaryImageGenerateKeywords build() {
            validateIsFilled();
            Map<String, String> keywords = new HashMap<>();
            keywords.put(GENDER_REGEX, gender);
            keywords.put(AGE_REGEX, age);
            keywords.put(EMOTION_REGEX, emotion);
            keywords.put(BEHAVIOR_REGEX, behavior);

            return new DiaryImageGenerateKeywords(keywords);
        }

        private void validateIsFilled() {
            if (!(StringUtils.hasText(gender) && StringUtils.hasText(age)
                    && StringUtils.hasText(emotion) && StringUtils.hasText(behavior))) {
                throw new KeywordCannotBeEmptyException();
            }
        }
    }
}
