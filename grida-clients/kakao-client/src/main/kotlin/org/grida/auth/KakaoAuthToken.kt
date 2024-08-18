package org.grida.auth

data class KakaoAuthToken(
    val accessToken: String,
    val refreshToken: String
)
