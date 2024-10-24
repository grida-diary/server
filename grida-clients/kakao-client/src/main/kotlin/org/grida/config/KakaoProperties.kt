package org.grida.config

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties("kakao")
data class KakaoProperties(
    val appKey: String,
    val baseUri: String,
    val apiPath: String
) {
    val redirectUri: String = baseUri + apiPath
}
