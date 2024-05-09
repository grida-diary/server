package org.grida.config;

import org.grida.authorizedrequest.AuthorizedRequest;

public interface WebSecurityConfigurer {

    void addPatterns(AuthorizedRequest authorizedRequest);
}
