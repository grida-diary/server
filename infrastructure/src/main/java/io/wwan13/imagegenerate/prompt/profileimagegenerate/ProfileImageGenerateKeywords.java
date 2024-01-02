package io.wwan13.imagegenerate.prompt.profileimagegenerate;

import io.wwan13.imagegenerate.exception.KeywordCannotBeEmptyException;
import io.wwan13.imagegenerate.prompt.Keywords;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

public class ProfileImageGenerateKeywords extends Keywords {

    private static final String GENDER_REGEX = "#GENDER";
    private static final String AGE_REGEX = "#AGE";

    private ProfileImageGenerateKeywords(Map<String, String> keywords) {
        super(keywords);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private String gender;
        private String age;

        public Builder gender(String gender) {
            this.gender = gender;
            return this;
        }

        public Builder age(int age) {
            this.age = Integer.toString(age);
            return this;
        }

        public ProfileImageGenerateKeywords build() {
            validateIsFilled();
            Map<String, String> keywords = new HashMap<>();
            keywords.put(GENDER_REGEX, gender);
            keywords.put(AGE_REGEX, age);

            return new ProfileImageGenerateKeywords(keywords);
        }

        private void validateIsFilled() {
            if (!(StringUtils.hasText(gender) && StringUtils.hasText(age))) {
                throw new KeywordCannotBeEmptyException();
            }
        }
    }
}
