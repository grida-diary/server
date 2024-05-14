package org.grida.authorizedrequest;

public class AuthorizedRequestApplier {

    private AuthorizedRequestApplier() {
    }

    public static AuthorizedRequest apply(AuthorizedRequestFactory factory) {
        return factory.apply();
    }
}
