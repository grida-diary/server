package org.grida.filter;

import lombok.RequiredArgsConstructor;
import org.grida.exception.ApisSecurityException;
import org.grida.jwt.TokenClaims;
import org.grida.jwt.TokenDecoder;
import org.grida.jwt.TokenValidator;
import org.grida.model.RequestMatcher;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.grida.exception.ApisSecurityErrorCode.HTTP_FORBIDDEN;
import static org.grida.exception.ApisSecurityErrorCode.HTTP_UNAUTHORIZED;

@RequiredArgsConstructor
public class AuthFilter extends OncePerRequestFilter {

    private static final String PROVIDER_ATTRIBUTE_KEY = "provider";
    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String AUTHENTICATION_TOKEN_PREFIX = "Bearer ";
    private static final int AUTHENTICATION_TOKEN_START_INDEX = 7;
    private static final long ANONYMOUS_USER_ID = -1L;
    private static final String ANONYMOUS_ROLE = "ROLE_ANONYMOUS";


    private final TokenDecoder tokenDecoder;
    private final TokenValidator tokenValidator;
    private final RequestMatcher requestMatcher;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        TokenClaims tokenClaims = decodeToken(request);
        validateRequestMatches(request.getRequestURI(), tokenClaims.role());
        if (tokenClaims.userId() != ANONYMOUS_USER_ID) {
            request.setAttribute(PROVIDER_ATTRIBUTE_KEY, tokenClaims.userId());
        }

        filterChain.doFilter(request, response);
    }

    private TokenClaims decodeToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(AUTHORIZATION_HEADER);

        if (StringUtils.hasText(bearerToken) || !bearerToken.startsWith(AUTHENTICATION_TOKEN_PREFIX)) {
            return new TokenClaims(ANONYMOUS_USER_ID, ANONYMOUS_ROLE);
        }

        String token = bearerToken.substring(AUTHENTICATION_TOKEN_START_INDEX);
        tokenValidator.validateToken(token);
        return tokenDecoder.decode(token);
    }

    private void validateRequestMatches(String requestUri, String role) {
        if (role.equals(ANONYMOUS_ROLE) && !requestMatcher.matches(requestUri, role)) {
            throw new ApisSecurityException(HTTP_UNAUTHORIZED);
        }
        if (!role.equals(ANONYMOUS_ROLE) && !requestMatcher.matches(requestUri, role)) {
            throw new ApisSecurityException(HTTP_FORBIDDEN);
        }
    }
}
