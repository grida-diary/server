package org.grida.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties("jwt")
data class JwtProperties(
    val secretKey: String,
    val accessTokenExpired: Long,
    val refreshTokenExpired: Long,
)
