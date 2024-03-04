package org.grida.filter;

import lombok.RequiredArgsConstructor;
import org.grida.jwt.TokenClaims;
import org.grida.jwt.TokenDecoder;
import org.grida.jwt.TokenValidator;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@RequiredArgsConstructor
public class DecodeTokenFilter extends OncePerRequestFilter {

    private static final String USER_EMAIL_ATTRIBUTE_KEY = "userEmail";
    private static final String USER_ROLE_ATTRIBUTE_KEY = "userRole";
    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String AUTHENTICATION_TOKEN_PREFIX = "Bearer ";
    private static final int AUTHENTICATION_TOKEN_START_INDEX = 7;

    private final TokenDecoder tokenDecoder;
    private final TokenValidator tokenValidator;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        decodeToken(request).ifPresent((tokenClaims) -> {
            request.setAttribute(USER_EMAIL_ATTRIBUTE_KEY, tokenClaims.userEmail());
            request.setAttribute(USER_ROLE_ATTRIBUTE_KEY, tokenClaims.role());
        });

        filterChain.doFilter(request, response);
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
