package org.grida.application.auth

data class LoginRequest(
    val code: String,
    val state: String
)
