package io.wwan13.storage.config;

import com.amazonaws.services.s3.AmazonS3;
import io.wwan13.storage.cdn.CdnProcessor;
import io.wwan13.storage.filenamegenerator.FileNameGenerator;
import io.wwan13.storage.filenamegenerator.uuid.UuidFileNameGenerator;
import io.wwan13.storage.uploader.ImageUploader;
import io.wwan13.storage.uploader.aws.S3ImageUploader;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StorageConfig {

    @Bean
    @ConditionalOnMissingBean
    public ImageUploader imageUploader(S3Properties properties,
                                       AmazonS3 amazonS3,
                                       FileNameGenerator fileNameGenerator) {
        return new S3ImageUploader(properties, amazonS3, fileNameGenerator);
    }

    @Bean
    @ConditionalOnMissingBean
    public CdnProcessor cdnProcessor(CdnProperties properties) {
        return new CdnProcessor(properties);
    }

    @Bean
    public FileNameGenerator fileNameGenerator() {
        return new UuidFileNameGenerator();
    }
}
