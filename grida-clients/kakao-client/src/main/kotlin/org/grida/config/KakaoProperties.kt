package org.grida.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties("kakao")
data class KakaoProperties(
    val appKey: String,
    val baseUri: String,
    val apiPath: String
) {
    val redirectUri: String = baseUri + apiPath
}
