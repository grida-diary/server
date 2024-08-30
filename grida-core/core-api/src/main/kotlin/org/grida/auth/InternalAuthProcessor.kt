package org.grida.auth

import org.grida.domain.user.LoginOption
import org.grida.domain.user.LoginPlatform
import org.grida.domain.user.UserService
import org.grida.error.GridaException
import org.grida.error.NotSupportedLoginPlatform
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class InternalAuthProcessor(
    @Value("\${spring.profiles.active}")
    private val activeProfile: String,
    private val userService: UserService,
    private val authTokenProvider: AuthTokenProvider
) : AuthProcessor {

    override fun process(
        code: String,
        state: String
    ): AuthToken {
        validateActiveProfile()
        val loginOption = LoginOption(LoginPlatform.ADMIN, code)
        val user = userService.read(loginOption.identifier.toLong())

        return authTokenProvider.provide(user)
    }

    private fun validateActiveProfile() {
        if (!enableProfiles.contains(activeProfile)) {
            throw throw GridaException(NotSupportedLoginPlatform)
        }
    }

    companion object {
        val enableProfiles = listOf("dev", "stag")
    }
}
