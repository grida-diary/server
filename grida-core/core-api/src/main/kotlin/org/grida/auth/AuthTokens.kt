package org.grida.auth

data class AuthTokens(
    val accessToken: String,
    val refreshToken: String,
)
