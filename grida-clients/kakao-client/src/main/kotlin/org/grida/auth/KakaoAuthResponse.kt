package org.grida.auth

import com.fasterxml.jackson.annotation.JsonProperty

data class KakaoAuthResponse(
    @JsonProperty("token_type")
    val tokenType: String,
    @JsonProperty("access_token")
    val accessToken: String,
    @JsonProperty("expires_in")
    val expiresId: Int,
    @JsonProperty("refresh_token")
    val refreshToken: String?,
    @JsonProperty("refresh_token_expires_in")
    val refreshTokenExpiredIn: Int?
) {

    fun toKakaoAuthToken(): KakaoAuthToken {
        return KakaoAuthToken(accessToken, refreshToken ?: "")
    }
}
