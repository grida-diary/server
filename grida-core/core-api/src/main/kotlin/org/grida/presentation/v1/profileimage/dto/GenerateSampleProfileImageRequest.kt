package org.grida.presentation.v1.profileimage.dto

import org.grida.domain.profileimage.Appearance
import org.grida.domain.profileimage.Gender

data class GenerateSampleProfileImageRequest(
    val gender: Gender,
    val age: Int,
    val hairStyle: String,
    val glasses: String,
    val bodyShape: String,
    val additional: String
) {
    fun toAppearance(): Appearance {
        return Appearance(gender, age, hairStyle, glasses, bodyShape, additional)
    }
}
