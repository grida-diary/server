package org.grida.auth

import io.wwan13.wintersecurity.jwt.TokenGenerator
import io.wwan13.wintersecurity.passwordencoder.PasswordEncoder
import org.grida.domain.user.UserService
import org.grida.exception.LoginFailedException
import org.springframework.stereotype.Component

@Component
class AuthTokenProvider(
    private val tokenGenerator: TokenGenerator,
    private val userService: UserService,
    private val passwordEncoder: PasswordEncoder
) {

    fun provide(
        username: String,
        password: String
    ): AuthTokens {
        val user = userService.findUser(username)
        if (!passwordEncoder.matches(password, user.password)) throw LoginFailedException()

        val tokenPayload = TokenPayload(user.id, user.role)
        return AuthTokens(
            accessToken = tokenGenerator.accessToken(tokenPayload),
            refreshToken = tokenGenerator.refreshToken(tokenPayload)
        )
    }
}
