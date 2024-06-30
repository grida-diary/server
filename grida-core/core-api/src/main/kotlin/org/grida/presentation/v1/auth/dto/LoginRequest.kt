package org.grida.presentation.v1.auth.dto

data class LoginRequest(
    val username: String,
    val password: String
)
