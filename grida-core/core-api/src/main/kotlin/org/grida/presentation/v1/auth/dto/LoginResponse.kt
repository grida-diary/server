package org.grida.presentation.v1.auth.dto

data class LoginResponse(
    val accessToken: String,
    val refreshToken: String,
)
