package org.grida.prompt;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class PromptKeywords {

    private final Map<String, String> keywords;

    public PromptKeywords(Map<String, String> keywords) {
        this.keywords = keywords;
    }

    public static Builder with() {
        return new Builder();
    }

    public Set<String> getKeys() {
        return Collections.unmodifiableSet(keywords.keySet());
    }

    public String getValueByKey(String key) {
        return keywords.get(key);
    }

    public static class Builder {

        private final Map<String, String> keywords;

        public Builder() {
            this.keywords = new HashMap<>();
        }

        public Builder keyword(String key, String value) {
            keywords.put(key, value);
            return this;
        }

        public PromptKeywords build() {
            return new PromptKeywords(keywords);
        }
    }
}
