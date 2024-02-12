package org.grida.authorizedrequest;

import org.springframework.http.HttpMethod;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static org.grida.authorizedrequest.BaseAuthorizedPatterns.AUTHENTICATED;
import static org.grida.authorizedrequest.BaseAuthorizedPatterns.PERMIT_ALL;
import static org.springframework.http.HttpMethod.*;

public class AuthorizedRequestBuilder {

    private static final Set<HttpMethod> allHttpMethods = Set.of(GET, POST, PATCH, PUT, DELETE);

    private final Map<MethodAndPattern, Set<String>> patternAndRoles;

    private AuthorizedRequestBuilder() {
        this.patternAndRoles = new LinkedHashMap<>();
    }

    public static AuthorizedRequestBuilder withPatterns() {
        return new AuthorizedRequestBuilder();
    }

    public AuthorizedRequestBuilder antMatchers(String pattern, Set<String> hasRoles) {
        patternAndRoles.put(new MethodAndPattern(allHttpMethods, pattern), hasRoles);
        return this;
    }

    public AuthorizedRequestBuilder antMatchers(
            Set<HttpMethod> httpMethods,
            String pattern,
            Set<String> hasRoles
    ) {
        patternAndRoles.put(new MethodAndPattern(httpMethods, pattern), hasRoles);
        return this;
    }

    public AuthorizedRequest elseRequestPermit() {
        return new AuthorizedRequest(patternAndRoles, true);
    }

    public AuthorizedRequest elseRequestAuthenticated() {
        return new AuthorizedRequest(patternAndRoles, false);
    }

    public static class Support {

        private Support() {
        }

        public static Set<String> permitAll() {
            return Set.of(PERMIT_ALL.getPattern());
        }

        public static Set<String> authenticated() {
            return Set.of(AUTHENTICATED.getPattern());
        }

        public static Set<String> hasRoles(Object... roles) {
            return Arrays.stream(roles)
                    .map(Object::toString)
                    .collect(Collectors.toSet());
        }

        public static Set<HttpMethod> httpMethods(HttpMethod... methods) {
            return Set.of(methods);
        }

        public static Set<HttpMethod> allHttpMethods() {
            return allHttpMethods;
        }
    }
}
