package org.grida.presentation.v1.profileimage

import org.grida.domain.profileimage.Appearance

data class GenerateSampleProfileImageRequest(
    val gender: Appearance.Gender,
    val age: Int,
    val hairStyle: String,
    val glasses: String,
    val bodyShape: String,
) {
    fun toAppearance(): Appearance {
        return Appearance(gender, age, hairStyle, glasses, bodyShape)
    }
}
