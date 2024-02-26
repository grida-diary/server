package org.grida;

import org.grida.config.CdnProperties;
import org.springframework.stereotype.Component;

@Component
public class CdnClient {

    private static final String URL_FORMAT = "%s%s";

    private final String host;

    public CdnClient(CdnProperties cdnProperties) {
        this.host = cdnProperties.getHost();
    }

    public String getUrl(String filePath) {
        return String.format(URL_FORMAT, host, filePath);
    }
}
