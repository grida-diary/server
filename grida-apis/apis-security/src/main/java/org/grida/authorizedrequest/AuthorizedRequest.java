package org.grida.authorizedrequest;

import org.springframework.http.HttpMethod;
import org.springframework.util.AntPathMatcher;

import java.util.Map;
import java.util.Set;

import static org.grida.authorizedrequest.BaseAuthorizedPatterns.AUTHENTICATED;
import static org.grida.authorizedrequest.BaseAuthorizedPatterns.PERMIT_ALL;

public class AuthorizedRequest {

    private static final MethodAndPattern NOT_REGISTERED = new MethodAndPattern(Set.of(), "NOT_REGISTERED");
    private static final String ANONYMOUS_ROLE = "ROLE_ANONYMOUS";

    private final AntPathMatcher antPathMatcher;
    private final Map<MethodAndPattern, Set<String>> registered;
    private final boolean isElseRequestPermit;

    protected AuthorizedRequest(Map<MethodAndPattern, Set<String>> registered,
                                boolean isElseRequestPermit) {
        this.antPathMatcher = new AntPathMatcher();
        this.registered = registered;
        this.isElseRequestPermit = isElseRequestPermit;
    }

    public boolean matches(String method, String requestUri, String role) {
        MethodAndPattern matched = registered.keySet().stream()
                .filter(methodAndPattern ->
                        methodAndPattern.isRegistered(HttpMethod.resolve(method), requestUri, antPathMatcher))
                .findFirst()
                .orElse(NOT_REGISTERED);

        if (matched.equals(NOT_REGISTERED)) {
            return isElseRequestPermit;
        }

        return registered.get(matched).stream()
                .anyMatch(registeredRole ->
                        checkPermitAll(registeredRole) || checkAuthenticated(registeredRole, role) ||
                                hasRole(registeredRole, role)
                );
    }

    private boolean checkPermitAll(String registeredRole) {
        return registeredRole.equals(PERMIT_ALL.getPattern());
    }

    private boolean checkAuthenticated(String registeredRole, String enteredRole) {
        return registeredRole.equals(AUTHENTICATED.getPattern()) && !enteredRole.equals(ANONYMOUS_ROLE);
    }

    private boolean hasRole(String registeredRole, String enteredRole) {
        return registeredRole.equals(enteredRole);
    }
}
