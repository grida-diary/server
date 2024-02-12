package org.grida.authorizedrequest;

import org.springframework.http.HttpMethod;
import org.springframework.util.AntPathMatcher;

import java.util.Set;

public record MethodAndPattern(
        Set<HttpMethod> methods,
        String pattern
) {

    public boolean isRegistered(HttpMethod method, String requestUri, AntPathMatcher antPathMatcher) {
        return antPathMatcher.match(pattern, requestUri) && methods.contains(method);
    }
}
