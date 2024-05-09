package org.grida.authorizedrequest;

import org.springframework.http.HttpMethod;
import org.springframework.util.AntPathMatcher;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class AuthorizedRequest {

    private static final MethodAndPattern NOT_REGISTERED = new MethodAndPattern(Set.of(), "NOT_REGISTERED");
    private static final boolean DEFAULT_ELSE_REQUEST_OPTION = true;

    private final AntPathMatcher antPathMatcher;
    private final Map<MethodAndPattern, Roles> registered;
    private boolean isElseRequestPermit;

    protected AuthorizedRequest(
            AntPathMatcher antPathMatcher,
            Map<MethodAndPattern, Roles> registered,
            boolean isElseRequestPermit
    ) {
        this.antPathMatcher = antPathMatcher;
        this.registered = registered;
        this.isElseRequestPermit = isElseRequestPermit;
    }

    public static AuthorizedRequest of() {
        return new AuthorizedRequest(
                new AntPathMatcher(),
                new HashMap<>(),
                DEFAULT_ELSE_REQUEST_OPTION
        );
    }

    public AuthorizedRequest addPattern(
            Set<HttpMethod> httpMethods,
            Set<String> patterns,
            Roles hasRoles
    ) {
        patterns.forEach(pattern -> registered.put(new MethodAndPattern(httpMethods, pattern), hasRoles));
        return this;
    }

    public AuthorizedRequestSupporter.HttpMethodAppender uriPatterns(String... uriPatterns) {
        return new AuthorizedRequestSupporter.HttpMethodAppender(
                this,
                Set.of(uriPatterns)
        );
    }

    public void elseRequestPermit() {
        this.isElseRequestPermit = true;
    }

    public void elseRequestAuthenticated() {
        this.isElseRequestPermit = false;
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
