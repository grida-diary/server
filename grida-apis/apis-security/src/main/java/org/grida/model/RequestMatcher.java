package org.grida.model;

import org.springframework.util.AntPathMatcher;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class RequestMatcher {

    private static final String ALL_ROLES = "*";
    private static final String NOT_REGISTERED_PATTERN = "NOT_REGISTERED";

    private final AntPathMatcher antPathMatcher;
    private final Map<String, Set<String>> patternAndRoles;
    private final boolean isElseRequestPermit;

    private RequestMatcher(AntPathMatcher antPathMatcher,
                           Map<String, Set<String>> patternAndRoles,
                           boolean isElseRequestPermit) {
        this.antPathMatcher = antPathMatcher;
        this.patternAndRoles = patternAndRoles;
        this.isElseRequestPermit = isElseRequestPermit;
    }

    public static Builder builder() {
        return new Builder();
    }

    public boolean matches(String requestUri, String role) {

        String matchedPattern = patternAndRoles.keySet().stream()
                .filter(pattern -> antPathMatcher.match(pattern, requestUri))
                .findFirst()
                .orElse(NOT_REGISTERED_PATTERN);

        if (matchedPattern.equals(NOT_REGISTERED_PATTERN)) {
            return isElseRequestPermit;
        }

        return patternAndRoles.get(matchedPattern).stream()
                .anyMatch(registeredRole ->
                        registeredRole.equals(role) || registeredRole.equals(ALL_ROLES));
    }

    public static class Builder {

        private final Map<String, Set<String>> patternAndRoles;

        Builder() {
            this.patternAndRoles = new LinkedHashMap<>();
        }

        public Builder antMatcher(String pattern, Object... roles) {
            patternAndRoles.put(
                    pattern,
                    Arrays.stream(roles)
                            .map(Object::toString)
                            .collect(Collectors.toUnmodifiableSet())
            );
            return this;
        }

        public RequestMatcher elsePermit() {
            return new RequestMatcher(
                    new AntPathMatcher(),
                    patternAndRoles,
                    true
            );
        }

        public RequestMatcher elseAuthenticated() {
            return new RequestMatcher(
                    new AntPathMatcher(),
                    patternAndRoles,
                    false
            );
        }
    }
}
