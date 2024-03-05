package org.grida.config;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@Getter
@RequiredArgsConstructor
@ConstructorBinding
@ConfigurationProperties(prefix = "storage.aws")
public class StorageProperties {

    private final String accessKey;
    private final String secretKey;
    private final String region;
    private final String bucket;
}
