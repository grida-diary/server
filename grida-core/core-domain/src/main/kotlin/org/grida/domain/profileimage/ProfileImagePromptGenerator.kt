package org.grida.domain.profileimage

import org.springframework.stereotype.Component

@Component
class ProfileImagePromptGenerator {

    fun generate(appearance: Appearance): String {
        return """
            Please generate a cozy style profile illustration
            for a ${appearance.age}-year-old Korean ${appearance.gender} according to the following description.
            HairStyle is ${appearance.hairStyle}.
            has ${appearance.glasses}
            only one person looking directly front. \n" +
            The background is plain white without decorations, highlighting the main subject.
            This simplicity enhances the cozy and homely feel of the artwork.
        """.trimIndent()
    }
}
