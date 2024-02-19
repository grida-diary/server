package org.grida.profileimage;

import lombok.Builder;
import org.grida.base.KeywordBuilder;
import org.grida.base.Keywords;

@Builder
public record ProfileImageAppearance(
        int age,
        String gender,
        String hairStyle,
        String hairLength,
        String hairColor,
        String glasses
) {

    public Keywords toKeywords() {
        return KeywordBuilder.profileImageGenerate()
                .age(age)
                .gender(gender)
                .hairStyle(combinedHairStyle())
                .glasses(glasses)
                .build();
    }

    private String combinedHairStyle() {
        return String.join(" ", hairStyle, hairLength, hairColor);
    }
}
