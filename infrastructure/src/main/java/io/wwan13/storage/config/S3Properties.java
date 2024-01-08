package io.wwan13.storage.config;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@Getter
@RequiredArgsConstructor
@ConfigurationProperties(prefix = "storage.aws")
@ConstructorBinding
public class S3Properties {

    private final String accessKey;
    private final String secretKey;
    private final String region;
    private final String bucket;
}
