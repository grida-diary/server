package org.grida.auth

import io.wwan13.wintersecurity.jwt.TokenGenerator
import org.grida.domain.user.User
import org.springframework.stereotype.Component

@Component
class AuthTokenProvider(
    private val tokenGenerator: TokenGenerator
) {

    fun provide(
        user: User
    ): AuthToken {
        val tokenPayload = TokenPayload(user.id, user.role)
        return AuthToken(
            accessToken = tokenGenerator.accessToken(tokenPayload),
            refreshToken = tokenGenerator.refreshToken(tokenPayload)
        )
    }
}
