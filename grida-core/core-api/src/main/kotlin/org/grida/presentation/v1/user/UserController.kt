package org.grida.presentation.v1.user

import io.wwan13.wintersecurity.passwordencoder.PasswordEncoder
import org.grida.api.ApiResponse
import org.grida.api.IdResponse
import org.grida.domain.user.Role
import org.grida.domain.user.User
import org.grida.domain.user.UserService
import org.grida.exception.PasswordConfirmNotMatchedException
import org.grida.presentation.v1.user.dto.SignInRequest
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/user")
class UserController(
    private val userService: UserService,
    private val passwordEncoder: PasswordEncoder
) {

    @PostMapping
    fun signIn(
        @RequestBody request: SignInRequest
    ): ApiResponse<IdResponse> {
        val user = User(
            username = request.username,
            password = passwordEncoder.encode(request.password),
            nickname = request.username,
            role = Role.USER
        )
        val id = userService.appendNormalUser(user, request.passwordConfirm)
        return ApiResponse.id(id)
    }
}
