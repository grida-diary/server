package org.grida.auth

data class AuthToken(
    val accessToken: String,
    val refreshToken: String
)
