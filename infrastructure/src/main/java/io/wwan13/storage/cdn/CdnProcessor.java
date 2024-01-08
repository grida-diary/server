package io.wwan13.storage.cdn;

import io.wwan13.storage.config.CdnProperties;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CdnProcessor {

    private final CdnProperties properties;

    public String getUrl(String filePath) {
        StringBuilder builder = new StringBuilder(properties.getDomain());
        builder.append(filePath);

        return builder.toString();
    }
}
