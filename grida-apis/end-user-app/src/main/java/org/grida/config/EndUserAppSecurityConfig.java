package org.grida.config;

import io.wwan13.wintersecurity.auth.authpattern.support.AuthPatternsRegistry;
import io.wwan13.wintersecurity.config.EnableJwtProvider;
import io.wwan13.wintersecurity.config.EnableSecureRequest;
import io.wwan13.wintersecurity.config.JwtProviderConfigurer;
import io.wwan13.wintersecurity.config.SecureRequestConfigurer;
import io.wwan13.wintersecurity.jwt.support.JwtPropertiesRegistry;
import io.wwan13.wintersecurity.resolve.support.TargetAnnotationsRegistry;
import io.wwan13.wintersecurity.secretkey.support.SecretKeyRegistry;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableSecureRequest
@EnableJwtProvider
public class EndUserAppSecurityConfig implements SecureRequestConfigurer, JwtProviderConfigurer {

    private final String secretKey;
    private final long accessTokenValidity;
    private final long refreshTokenValidity;

    public EndUserAppSecurityConfig(
            @Value("${JWT_SECRET_KEY}") String secretKey,
            @Value("${ACCESS_TOKEN_EXPIRED}") long accessTokenValidity,
            @Value("${REFRESH_TOKEN_EXPIRED}") long refreshTokenValidity
    ) {
        this.secretKey = secretKey;
        this.accessTokenValidity = accessTokenValidity;
        this.refreshTokenValidity = refreshTokenValidity;
    }

    @Override
    public void registerAuthPatterns(AuthPatternsRegistry registry) {
        registry
                .elseRequestPermit();
    }

    @Override
    public void configureSecretKey(SecretKeyRegistry registry) {
        registry
                .secretKey(secretKey);
    }

    @Override
    public void configureJwt(JwtPropertiesRegistry registry) {
        registry
                .accessTokenValidity(accessTokenValidity)
                .refreshTokenValidity(refreshTokenValidity);
    }
}
