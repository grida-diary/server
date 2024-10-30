package org.grida.config

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties("jwt")
data class JwtProperties(
    val secretKey: String,
    val accessTokenExpired: Long,
    val refreshTokenExpired: Long,
)
