package org.grida.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties("storage.aws")
data class StorageClientProperties(
    val accessKey: String,
    val secretKey: String,
    val region: String,
    val bucket: String,
    val host: String
)
