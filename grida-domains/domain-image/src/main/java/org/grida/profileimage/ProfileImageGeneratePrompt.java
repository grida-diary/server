package org.grida.profileimage;

import org.grida.base.Prompt;
import org.springframework.stereotype.Component;

@Component
public class ProfileImageGeneratePrompt extends Prompt {

    public ProfileImageGeneratePrompt() {
        super(
                "Please generate a cozy style profile illustration " +
                        "for a #AGE#-year-old Koean #GENDER# according to the following description. \n" +
                        "only one person looking directly front. \n" +
                        "The background is plain white without decorations, highligting the main subject. \n" +
                        "This simplicity enhances the cozy and homely feel of the artwork. \n" +
                        "HairStyle is #HAIR_STYLE# hair." +
                        "Wearing #GLASSES# \n"
        );
    }
}
