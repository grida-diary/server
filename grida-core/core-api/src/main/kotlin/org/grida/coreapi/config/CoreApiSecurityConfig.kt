package org.grida.coreapi.config

import io.wwan13.wintersecurity.auth.authpattern.support.AuthPatternsRegistry
import io.wwan13.wintersecurity.config.EnableJwtProvider
import io.wwan13.wintersecurity.config.EnableSecureRequest
import io.wwan13.wintersecurity.config.JwtProviderConfigurer
import io.wwan13.wintersecurity.config.SecureRequestConfigurer
import io.wwan13.wintersecurity.jwt.support.JwtPropertiesRegistry
import io.wwan13.wintersecurity.secretkey.support.SecretKeyRegistry
import org.springframework.context.annotation.Configuration

@Configuration
@EnableSecureRequest
@EnableJwtProvider
class CoreApiSecurityConfig :
    SecureRequestConfigurer,
    JwtProviderConfigurer {
    override fun configureSecretKey(registry: SecretKeyRegistry) {
        registry
            .secretKey("secretKeyeyeyeyeyeyeyeyeyeyeyeye")
    }

    override fun configureJwt(registry: JwtPropertiesRegistry) {
        registry.apply {
            accessTokenValidity(100000000L)
            refreshTokenValidity(200000000L)
        }
    }

    override fun registerAuthPatterns(registry: AuthPatternsRegistry) {
        registry.apply {
            elseRequestPermit()
        }
    }
}
