package org.grida.profileimage;

import org.grida.prompt.PromptKeywords;

public record ProfileImageGenerateKey(
        int age,
        String gender,
        String hairStyle,
        String hairLength,
        String hairColor,
        String glasses
) {

    private static final String HAIR_STYLE_JOIN_DELIMITER = " ";

    public PromptKeywords toPromptKeyword() {
        return PromptKeywords.with()
                .keyword("#AGE#", Integer.toString(age))
                .keyword("#GENDER#", gender)
                .keyword("#HAIR_STYLE#", String.join(HAIR_STYLE_JOIN_DELIMITER, hairLength, hairColor, hairStyle))
                .keyword("#GLASSES", glasses)
                .build();
    }
}
