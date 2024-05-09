package org.grida.authorizedrequest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;

import java.util.Arrays;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

import static org.grida.authorizedrequest.BaseAuthorizedPatterns.AUTHENTICATED;
import static org.grida.authorizedrequest.BaseAuthorizedPatterns.PERMIT_ALL;
import static org.springframework.http.HttpMethod.*;

@RequiredArgsConstructor
public class AuthorizedRequestSupporter {

    private static final Set<HttpMethod> ALL_HTTP_METHODS = Set.of(GET, POST, PATCH, PUT, DELETE);
    private static final Set<HttpMethod> HTTP_METHOD_GET = Collections.singleton(GET);
    private static final Set<HttpMethod> HTTP_METHOD_POST = Collections.singleton(POST);
    private static final Set<HttpMethod> HTTP_METHOD_PATCH = Collections.singleton(PATCH);
    private static final Set<HttpMethod> HTTP_METHOD_PUT = Collections.singleton(PUT);
    private static final Set<HttpMethod> HTTP_METHOD_DELETE = Collections.singleton(DELETE);

    private static final Roles ALL_ROLES = new Roles(Set.of(PERMIT_ALL.getPattern()));
    private static final Roles EMPTY_ROLES = new Roles(Set.of(AUTHENTICATED.getPattern()));

    public static class HttpMethodAppender {

        private final AuthorizedRequest authorizedRequest;
        private final Set<String> uriPatterns;

        HttpMethodAppender(AuthorizedRequest authorizedRequest, Set<String> uriPatterns) {
            this.authorizedRequest = authorizedRequest;
            this.uriPatterns = uriPatterns;
        }

        public RolesAppender httpMethods(HttpMethod... methods) {
            return new RolesAppender(
                    authorizedRequest,
                    Set.of(methods),
                    uriPatterns
            );
        }

        public RolesAppender allHttpMethods() {
            return new RolesAppender(
                    authorizedRequest,
                    ALL_HTTP_METHODS,
                    uriPatterns
            );
        }

        public RolesAppender httpMethodGet() {
            return new RolesAppender(
                    authorizedRequest,
                    HTTP_METHOD_GET,
                    uriPatterns
            );
        }

        public RolesAppender httpMethodPost() {
            return new RolesAppender(
                    authorizedRequest,
                    HTTP_METHOD_POST,
                    uriPatterns
            );
        }

        public RolesAppender httpMethodPatch() {
            return new RolesAppender(
                    authorizedRequest,
                    HTTP_METHOD_PATCH,
                    uriPatterns
            );
        }

        public RolesAppender httpMethodPut() {
            return new RolesAppender(
                    authorizedRequest,
                    HTTP_METHOD_PUT,
                    uriPatterns
            );
        }

        public RolesAppender httpMethodDelete() {
            return new RolesAppender(
                    authorizedRequest,
                    HTTP_METHOD_DELETE,
                    uriPatterns
            );
        }
    }

    public static class RolesAppender {

        private final AuthorizedRequest authorizedRequest;
        private final Set<HttpMethod> httpMethods;
        private final Set<String> uriPatterns;

        RolesAppender(AuthorizedRequest authorizedRequest, Set<HttpMethod> httpMethods, Set<String> uriPatterns) {
            this.authorizedRequest = authorizedRequest;
            this.httpMethods = httpMethods;
            this.uriPatterns = uriPatterns;
        }

        public AuthorizedRequest permitAll() {
            authorizedRequest.addPattern(
                    this.httpMethods,
                    this.uriPatterns,
                    ALL_ROLES
            );
            return authorizedRequest;
        }

        public AuthorizedRequest authenticated() {
            authorizedRequest.addPattern(
                    this.httpMethods,
                    this.uriPatterns,
                    EMPTY_ROLES
            );
            return authorizedRequest;
        }

        public AuthorizedRequest hasRoles(Object... roles) {
            authorizedRequest.addPattern(
                    this.httpMethods,
                    this.uriPatterns,
                    mappingRoles(roles)
            );
            return authorizedRequest;
        }

        private Roles mappingRoles(Object... roles) {
            return new Roles(
                    Arrays.stream(roles)
                            .map(Object::toString)
                            .collect(Collectors.toUnmodifiableSet())
            );
        }
    }
}
