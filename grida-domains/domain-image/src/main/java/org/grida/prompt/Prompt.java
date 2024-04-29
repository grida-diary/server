package org.grida.prompt;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Prompt {

    GENERATE_PROFILE_IMAGE(
            "Please generate a cozy style profile illustration " +
                    "for a #AGE#-year-old Koean #GENDER# according to the following description. \n" +
                    "only one person looking directly front. \n" +
                    "The background is plain white without decorations, highligting the main subject. \n" +
                    "This simplicity enhances the cozy and homely feel of the artwork. \n" +
                    "HairStyle is #HAIR_STYLE# hair." +
                    "Wearing #GLASSES# \n"
    ),
    DIARY_ANALYSIS(
            "Analyze my actions and emotions today in the diary below, " +
                    "and write a prompt to create a picture like this in delle in english. " +
                    "The picture I want is a cozy style illustration. " +
                    "The main character is a 23-year-old Korean man. " +
                    "\"#DIARY#\"" +
                    "The response format is in json format without markdown context, " +
                    "and prompt you created are contained in “prompt”"
    ),
    GENERATE_DIARY_IMAGE(
            "Draw a picture that matches the script below. " +
                    "The illustration is a warm style illustration. " +
                    "The main character of this picture should be the same person " +
                    "\"#DIARY_ANALYSIS_RESULT#\"" +
                    "as the main character of the image URL I provided." +
                    "(#MAIN_CHARACTER_IMAGE_URL#)"
    );

    private final String skeleton;
}
