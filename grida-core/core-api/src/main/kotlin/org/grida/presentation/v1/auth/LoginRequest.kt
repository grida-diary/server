package org.grida.presentation.v1.auth

data class LoginRequest(
    val code: String,
    val state: String
)
