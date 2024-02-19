package org.grida.profileimage;

import org.grida.base.KeywordBuilder;

import java.util.Set;

public class ProfileImageGenerateKeywordBuilder extends KeywordBuilder {

    private static final String GENDER_KEY = "#GENDER";
    private static final String AGE_KEY = "#AGE";
    private static final String HAIR_STYLE_KEY = "#HAIR_STYLE";
    private static final String GLASSES_KEY = "#GLASSES";

    public ProfileImageGenerateKeywordBuilder gender(String gender) {
        super.addKeyword(GENDER_KEY, gender);
        return this;
    }

    public ProfileImageGenerateKeywordBuilder age(int age) {
        addKeyword(AGE_KEY, String.valueOf(age));
        return this;
    }

    public ProfileImageGenerateKeywordBuilder hairStyle(String hairStyle) {
        addKeyword(HAIR_STYLE_KEY, hairStyle);
        return this;
    }

    public ProfileImageGenerateKeywordBuilder glasses(String glasses) {
        addKeyword(GLASSES_KEY, glasses);
        return this;
    }

    @Override
    protected Set<String> allKeys() {
        return Set.of(GENDER_KEY, AGE_KEY, HAIR_STYLE_KEY, GLASSES_KEY);
    }
}
