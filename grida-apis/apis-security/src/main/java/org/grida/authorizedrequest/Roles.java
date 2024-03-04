package org.grida.authorizedrequest;

import java.util.Set;

import static org.grida.authorizedrequest.BaseAuthorizedPatterns.AUTHENTICATED;
import static org.grida.authorizedrequest.BaseAuthorizedPatterns.PERMIT_ALL;

public record Roles(
        Set<String> roles
) {

    private static final String ANONYMOUS_ROLE = "ROLE_ANONYMOUS";

    public boolean isAccessibleRole(String enteredRole) {
        return roles.stream()
                .anyMatch(role ->
                        checkPermitAll(role) || checkAuthenticated(role, enteredRole) || hasRole(role, enteredRole));
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
