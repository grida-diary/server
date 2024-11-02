package org.grida.config

import io.wwan13.wintersecurity.config.EnableJwtProvider
import io.wwan13.wintersecurity.config.JwtProviderConfigurer
import io.wwan13.wintersecurity.jwt.support.JwtPropertiesRegistry
import io.wwan13.wintersecurity.secretkey.support.SecretKeyRegistry
import org.springframework.context.annotation.Configuration

@Configuration
@EnableJwtProvider
class CoreApiJwtConfig(
    private val jwtProperties: JwtProperties
) : JwtProviderConfigurer {

    override fun configureSecretKey(registry: SecretKeyRegistry) {
        registry
            .secretKey(jwtProperties.secretKey)
    }

    override fun configureJwt(registry: JwtPropertiesRegistry) {
        registry.apply {
            accessTokenValidity(jwtProperties.accessTokenExpired)
            refreshTokenValidity(jwtProperties.refreshTokenExpired)
        }
    }
}
