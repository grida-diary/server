package org.grida.domain.user

data class UserProfile(
    val nickname: String = "",
    val gender: Gender = Gender.UNKNOWN,
    val age: Int = 0,
) {

    enum class Gender {
        MAN, WOMAN, UNKNOWN
    }
}
