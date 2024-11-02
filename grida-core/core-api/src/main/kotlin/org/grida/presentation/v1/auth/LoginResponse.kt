package org.grida.presentation.v1.auth

data class LoginResponse(
    val accessToken: String,
    val refreshToken: String
)
