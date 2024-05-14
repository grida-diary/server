package org.grida.config;

import org.grida.authorizedrequest.AuthorizedRequestFactory;

public interface WebSecurityConfigurer {

    void addPatterns(AuthorizedRequestFactory factory);
}
