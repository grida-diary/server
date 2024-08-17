package org.grida.presentation.v1.auth

import io.wwan13.wintersecurity.jwt.TokenGenerator
import io.wwan13.wintersecurity.passwordencoder.PasswordEncoder
import org.grida.api.ApiResponse
import org.grida.config.TokenPayload
import org.grida.domain.user.UserService
import org.grida.error.LoginFailed
import org.grida.presentation.v1.auth.dto.LoginRequest
import org.grida.presentation.v1.auth.dto.LoginResponse
import org.grida.validator.requestvalidator.RequestValidator
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/auth")
class AuthController(
    private val tokenGenerator: TokenGenerator,
    private val userService: UserService,
    private val passwordEncoder: PasswordEncoder
) {

    @PostMapping("/login")
    fun login(
        @RequestBody request: LoginRequest
    ): ApiResponse<LoginResponse> {
        val user = userService.findUser(request.username)
        RequestValidator.validate(LoginFailed) {
            passwordEncoder.matches(request.password, user.password)
        }

        val tokenPayload = TokenPayload(user.id, user.role)
        val response = LoginResponse(
            accessToken = tokenGenerator.accessToken(tokenPayload),
            refreshToken = tokenGenerator.refreshToken(tokenPayload)
        )
        return ApiResponse.success(response)
    }
}
