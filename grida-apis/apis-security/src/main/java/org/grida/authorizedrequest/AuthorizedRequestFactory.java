package org.grida.authorizedrequest;

import org.springframework.http.HttpMethod;
import org.springframework.util.AntPathMatcher;

import java.util.*;
import java.util.stream.Collectors;

import static org.grida.authorizedrequest.BaseAuthorizedPatterns.AUTHENTICATED;
import static org.grida.authorizedrequest.BaseAuthorizedPatterns.PERMIT_ALL;
import static org.springframework.http.HttpMethod.*;

public class AuthorizedRequestFactory {

    private static final Set<HttpMethod> ALL_HTTP_METHODS = Set.of(GET, POST, PATCH, PUT, DELETE);
    private static final Set<HttpMethod> HTTP_METHOD_GET = Collections.singleton(GET);
    private static final Set<HttpMethod> HTTP_METHOD_POST = Collections.singleton(POST);
    private static final Set<HttpMethod> HTTP_METHOD_PATCH = Collections.singleton(PATCH);
    private static final Set<HttpMethod> HTTP_METHOD_PUT = Collections.singleton(PUT);
    private static final Set<HttpMethod> HTTP_METHOD_DELETE = Collections.singleton(DELETE);

    private static final Set<Object> ALL_ROLES = Collections.singleton(PERMIT_ALL.getPattern());
    private static final Set<Object> EMPTY_ROLES = Collections.singleton(AUTHENTICATED.getPattern());

    private static final boolean DEFAULT_ELSE_REQUEST_OPTION = true;

    private Set<String> uriPatterns;
    private Set<HttpMethod> httpMethods;
    private Set<Object> roles;
    private boolean isElseRequestPermit = DEFAULT_ELSE_REQUEST_OPTION;

    public static AuthorizedRequestFactory of() {
        return new AuthorizedRequestFactory();
    }

    public HttpMethodAppender uriPatterns(String... uriPatterns) {
        this.uriPatterns = Set.of(uriPatterns);
        return new HttpMethodAppender(this);
    }

    public void elseRequestPermit() {
        this.isElseRequestPermit = true;
    }

    public void elseRequestAuthenticated() {
        this.isElseRequestPermit = false;
    }

    protected AuthorizedRequest apply() {
        Map<MethodAndPattern, Roles> registered = new HashMap<>();
        uriPatterns.forEach(pattern ->
                registered.put(new MethodAndPattern(httpMethods, pattern), mapRoles()));

        return new AuthorizedRequest(
                new AntPathMatcher(),
                registered,
                isElseRequestPermit
        );
    }

    private Roles mapRoles() {
        return new Roles(
                roles.stream()
                        .map(Object::toString)
                        .collect(Collectors.toUnmodifiableSet())
        );
    }

    public static class HttpMethodAppender {

        private final AuthorizedRequestFactory factory;

        HttpMethodAppender(AuthorizedRequestFactory factory) {
            this.factory = factory;
        }

        public RolesAppender httpMethods(HttpMethod... methods) {
            factory.httpMethods = Set.of(methods);
            return new RolesAppender(factory);
        }

        public RolesAppender allHttpMethods() {
            factory.httpMethods = ALL_HTTP_METHODS;
            return new RolesAppender(factory);
        }

        public RolesAppender httpMethodGet() {
            factory.httpMethods = HTTP_METHOD_GET;
            return new RolesAppender(factory);
        }

        public RolesAppender httpMethodPost() {
            factory.httpMethods = HTTP_METHOD_POST;
            return new RolesAppender(factory);
        }

        public RolesAppender httpMethodPatch() {
            factory.httpMethods = HTTP_METHOD_PATCH;
            return new RolesAppender(factory);
        }

        public RolesAppender httpMethodPut() {
            factory.httpMethods = HTTP_METHOD_PUT;
            return new RolesAppender(factory);
        }

        public RolesAppender httpMethodDelete() {
            factory.httpMethods = HTTP_METHOD_DELETE;
            return new RolesAppender(factory);
        }
    }

    public static class RolesAppender {

        private final AuthorizedRequestFactory factory;

        RolesAppender(AuthorizedRequestFactory factory) {
            this.factory = factory;
        }

        public AuthorizedRequestFactory permitAll() {
            factory.roles = ALL_ROLES;
            return factory;
        }

        public AuthorizedRequestFactory authenticated() {
            factory.roles = EMPTY_ROLES;
            return factory;
        }

        public AuthorizedRequestFactory hasRoles(Object... roles) {
            factory.roles = Set.of(roles);
            return factory;
        }
    }
}
