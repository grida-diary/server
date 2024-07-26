package org.grida.config

import io.wwan13.wintersecurity.auth.authpattern.support.AuthPatternsRegistry
import io.wwan13.wintersecurity.config.EnableJwtProvider
import io.wwan13.wintersecurity.config.EnableSecureRequest
import io.wwan13.wintersecurity.config.JwtProviderConfigurer
import io.wwan13.wintersecurity.config.SecureRequestConfigurer
import io.wwan13.wintersecurity.jwt.support.JwtPropertiesRegistry
import io.wwan13.wintersecurity.secretkey.support.SecretKeyRegistry
import org.grida.domain.user.Role
import org.springframework.context.annotation.Configuration

@Configuration
@EnableSecureRequest
@EnableJwtProvider
class CoreApiSecurityConfig(
    private val jwtProperties: JwtProperties
) : SecureRequestConfigurer, JwtProviderConfigurer {

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

    override fun registerAuthPatterns(registry: AuthPatternsRegistry) {
        registry.apply {
            uriPatterns("/api/v1/auth/login", "/api/v1/user")
                .httpMethodPost()
                .permitAll()

            uriPatterns("/api/v1/user/image/**", "/api/v1/diary/**")
                .allHttpMethods()
                .hasRoles(Role.ROLE_USER)

            elseRequestAuthenticated()
        }
    }
}
