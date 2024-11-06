package org.grida.application.auth

data class LoginResponse(
    val accessToken: String,
    val refreshToken: String
)
