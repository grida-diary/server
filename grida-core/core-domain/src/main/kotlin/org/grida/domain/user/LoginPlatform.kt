package org.grida.domain.user

import org.grida.domain.base.ValueEnum

enum class LoginPlatform(
    override val value: String
) : ValueEnum<LoginPlatform> {
    KAKAO("kakao"),
    ADMIN("admin")
}
