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

    private final Map<MethodAndPattern, Roles> patternAndRoles;

    private AuthorizedRequestBuilder() {
        this.patternAndRoles = new LinkedHashMap<>();
    }

    public static AuthorizedRequestBuilder withPatterns() {
        return new AuthorizedRequestBuilder();
    }

    public AuthorizedRequestBuilder antMatchers(
            Set<HttpMethod> httpMethods,
            Set<String> patterns,
            Roles hasRoles
    ) {
        patterns.forEach(pattern -> {
            patternAndRoles.put(new MethodAndPattern(httpMethods, pattern), hasRoles);
        });
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

        public static Roles permitAll() {
            return new Roles(Set.of(PERMIT_ALL.getPattern()));
        }

        public static Roles authenticated() {
            return new Roles(Set.of(AUTHENTICATED.getPattern()));
        }

        public static Roles hasRoles(Object... roles) {
            return new Roles(
                    Arrays.stream(roles)
                            .map(Object::toString)
                            .collect(Collectors.toSet())
            );
        }

        public static Set<HttpMethod> httpMethods(HttpMethod... methods) {
            return Set.of(methods);
        }

        public static Set<HttpMethod> allHttpMethods() {
            return Set.of(GET, POST, PATCH, PUT, DELETE);
        }

        public static Set<String> uriPatterns(String... uriPattern) {
            return Set.of(uriPattern);
        }
    }
}
