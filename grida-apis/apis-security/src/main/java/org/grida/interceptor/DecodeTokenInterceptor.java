package org.grida.interceptor;

import lombok.RequiredArgsConstructor;
import org.grida.config.WebSecurityConfigurer;
import org.grida.jwt.TokenClaims;
import org.grida.jwt.TokenDecoder;
import org.grida.jwt.TokenValidator;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@Component
@ConditionalOnBean(value = {WebSecurityConfigurer.class})
@RequiredArgsConstructor
public class DecodeTokenInterceptor implements HandlerInterceptor {

    private static final String USER_EMAIL_ATTRIBUTE_KEY = "userEmail";
    private static final String USER_ROLE_ATTRIBUTE_KEY = "userRole";
    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String AUTHENTICATION_TOKEN_PREFIX = "Bearer ";
    private static final int AUTHENTICATION_TOKEN_START_INDEX = 7;

    private final TokenDecoder tokenDecoder;
    private final TokenValidator tokenValidator;

    @Override
    public boolean preHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler
    ) {

        decodeToken(request).ifPresent(tokenClaims -> {
            request.setAttribute(USER_EMAIL_ATTRIBUTE_KEY, tokenClaims.userEmail());
            request.setAttribute(USER_ROLE_ATTRIBUTE_KEY, tokenClaims.role());
        });

        return true;
    }

    private Optional<TokenClaims> decodeToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(AUTHORIZATION_HEADER);
        if (!hasToken(bearerToken) || !isValidTokenPrefix(bearerToken)) {
            return Optional.empty();
        }

        String token = bearerToken.substring(AUTHENTICATION_TOKEN_START_INDEX);
        tokenValidator.validateToken(token);
        return Optional.of(tokenDecoder.decode(token));
    }

    private boolean hasToken(String bearerToken) {
        return StringUtils.hasText(bearerToken);
    }

    private boolean isValidTokenPrefix(String bearerToken) {
        return bearerToken.startsWith(AUTHENTICATION_TOKEN_PREFIX);
    }
}
