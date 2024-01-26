package org.grida;

import org.grida.config.CdnProperties;
import org.springframework.stereotype.Component;

@Component
public class CdnClient {

    private static final String URL_FORMAT = "%s%s";

    private final String domain;

    public CdnClient(CdnProperties cdnProperties) {
        this.domain = cdnProperties.getDomain();
    }

    public String getUrl(String filePath) {
        return String.format(URL_FORMAT, domain, filePath);
    }
}
