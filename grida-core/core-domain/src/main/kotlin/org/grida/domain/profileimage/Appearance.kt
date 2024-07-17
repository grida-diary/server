package org.grida.domain.profileimage

data class Appearance(
    val gender: Gender,
    val age: Int,
    val hairStyle: String,
    val glasses: String,
    val bodyShape: String
)

enum class Gender {
    MAN, WOMAN
}
