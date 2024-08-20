package org.grida.config

import com.amazonaws.auth.AWSStaticCredentialsProvider
import com.amazonaws.auth.BasicAWSCredentials
import com.amazonaws.services.s3.AmazonS3
import com.amazonaws.services.s3.AmazonS3ClientBuilder
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationPropertiesScan
class S3ClientConfig(
    private val properties: S3ClientProperties,
) {
    @Bean
    fun amazonS3Client(properties: S3ClientProperties): AmazonS3 {
        val credential = BasicAWSCredentials(properties.accessKey, properties.secretKey)

        return AmazonS3ClientBuilder
            .standard()
            .apply {
                withCredentials(AWSStaticCredentialsProvider(credential))
                withRegion(properties.region)
            }.build()
    }
}
