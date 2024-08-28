package org.grida.presentation.v1.auth.dto

import org.grida.auth.AuthToken

data class LoginResponse(
    val accessToken: String,
    val refreshToken: String,
) {
    companion object {
        fun from(authToken: AuthToken): LoginResponse {
            return LoginResponse(authToken.accessToken, authToken.refreshToken)
        }
    }
}
