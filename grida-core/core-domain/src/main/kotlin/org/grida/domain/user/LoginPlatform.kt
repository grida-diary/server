package org.grida.domain.user

import org.grida.domain.base.ValueEnum

enum class LoginPlatform(
    override val value: String
) : ValueEnum<LoginPlatform> {
    KAKAO("카카오"),
    GOOGLE("구글"),
    GITHUB("깃허브");
}
