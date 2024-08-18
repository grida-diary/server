package org.grida.domain.user

import org.grida.domain.base.BaseEnum

enum class LoginPlatform(
    override val value: String
) : BaseEnum<LoginPlatform> {
    KAKAO("카카오"),
    GOOGLE("구글"),
    GITHUB("깃허브");
}
