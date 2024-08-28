package org.grida.auth

import org.grida.error.GridaException
import org.grida.error.NotSupportedLoginPlatform
import org.springframework.stereotype.Component

@Component
class AuthProcessorSelector(
    private val authProcessors: Map<String, AuthProcessor>
) {

    fun select(platform: String): AuthProcessor {
        return authProcessors["${platform}AuthProcessor"]
            ?: throw GridaException(NotSupportedLoginPlatform)
    }
}
