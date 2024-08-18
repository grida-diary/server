package org.grida.domain.user

data class LoginOption(
    val platform: LoginPlatform,
    val identifier: String
)
