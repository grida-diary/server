package org.grida.base;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Keywords {

    private final Map<String, String> keywords;

    protected Keywords(Map<String, String> keywords) {
        this.keywords = new HashMap<>(keywords);
    }

    protected Set<String> getKeys() {
        return Collections.unmodifiableSet(keywords.keySet());
    }

    protected String getValues(String key) {
        return keywords.get(key);
    }
}
