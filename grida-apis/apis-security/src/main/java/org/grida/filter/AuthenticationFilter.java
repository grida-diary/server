package org.grida.filter;

import lombok.RequiredArgsConstructor;
import org.grida.exception.ApisSecurityException;
import org.grida.authorizedrequest.AuthorizedRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.grida.exception.ApisSecurityErrorCode.HTTP_FORBIDDEN;
import static org.grida.exception.ApisSecurityErrorCode.HTTP_UNAUTHORIZED;

@RequiredArgsConstructor
public class AuthenticationFilter extends OncePerRequestFilter {

    private static final String USER_ROLE_ATTRIBUTE_KEY = "userRole";
    private static final String ANONYMOUS_ROLE = "ROLE_ANONYMOUS";

    private final AuthorizedRequest authorizedRequest;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        String method = request.getMethod();
        String requestUri = request.getRequestURI();
        String role = readUserRole(request);

        validateUnauthorized(method, requestUri, role);
        validateForbidden(method, requestUri, role);

        filterChain.doFilter(request, response);
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
