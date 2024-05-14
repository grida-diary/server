package org.grida.authorizedrequest;

import org.springframework.http.HttpMethod;
import org.springframework.util.AntPathMatcher;

import java.util.Map;
import java.util.Set;

public class AuthorizedRequest {

    private static final MethodAndPattern NOT_REGISTERED = new MethodAndPattern(Set.of(), "NOT_REGISTERED");

    private final AntPathMatcher antPathMatcher;
    private final Map<MethodAndPattern, Roles> registered;
    private final boolean isElseRequestPermit;

    protected AuthorizedRequest(
            AntPathMatcher antPathMatcher,
            Map<MethodAndPattern, Roles> registered,
            boolean isElseRequestPermit
    ) {
        this.antPathMatcher = antPathMatcher;
        this.registered = registered;
        this.isElseRequestPermit = isElseRequestPermit;
    }

    public boolean isAccessibleRequest(String method, String requestUri, String role) {
        MethodAndPattern matched = registered.keySet().stream()
                .filter(methodAndPattern ->
                        methodAndPattern.isRegistered(HttpMethod.resolve(method), requestUri, antPathMatcher))
                .findFirst()
                .orElse(NOT_REGISTERED);

        if (matched.equals(NOT_REGISTERED)) {
            return isElseRequestPermit;
        }

        return registered.get(matched).isAccessibleRole(role);
    }
}
