package org.grida.authorizedrequest;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum BaseAuthorizedPatterns {

    PERMIT_ALL("*"),
    AUTHENTICATED("-");

    private final String pattern;
}
