package org.grida.interceptor;

import lombok.RequiredArgsConstructor;
import org.grida.authorizedrequest.AuthorizedRequest;
import org.grida.config.WebSecurityConfigurer;
import org.grida.exception.ApisSecurityException;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.grida.exception.ApisSecurityErrorCode.HTTP_FORBIDDEN;
import static org.grida.exception.ApisSecurityErrorCode.HTTP_UNAUTHORIZED;

@Component
@ConditionalOnBean(value = {WebSecurityConfigurer.class})
@RequiredArgsConstructor
public class AuthenticationInterceptor implements HandlerInterceptor {

    private static final String USER_ROLE_ATTRIBUTE_KEY = "userRole";
    private static final String ANONYMOUS_ROLE = "ROLE_ANONYMOUS";

    private final AuthorizedRequest authorizedRequest;

    @Override
    public boolean preHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler
    ) {
        String method = request.getMethod();
        String requestUri = request.getRequestURI();
        String role = readUserRole(request);

        validateUnauthorized(method, requestUri, role);
        validateForbidden(method, requestUri, role);

        return true;
    }

    private String readUserRole(HttpServletRequest request) {
        try {
            return request.getAttribute(USER_ROLE_ATTRIBUTE_KEY).toString();
        } catch (NullPointerException e) {
            return ANONYMOUS_ROLE;
        }
    }

    private void validateUnauthorized(String method, String requestUri, String role) {
        if (hasAuthentication(role) && !canAccess(method, requestUri, role)) {
            throw new ApisSecurityException(HTTP_UNAUTHORIZED);
        }
    }

    private void validateForbidden(String method, String requestUri, String role) {
        if (!hasAuthentication(role) && !canAccess(method, requestUri, role)) {
            throw new ApisSecurityException(HTTP_FORBIDDEN);
        }
    }

    private boolean hasAuthentication(String role) {
        return role.equals(ANONYMOUS_ROLE);
    }

    private boolean canAccess(String method, String requestUri, String role) {
        return authorizedRequest.isAccessibleRequest(method, requestUri, role);
    }
}
