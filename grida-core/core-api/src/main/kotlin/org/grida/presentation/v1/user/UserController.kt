package org.grida.presentation.v1.user

import org.grida.api.ApiResponse
import org.grida.api.IdResponse
import org.grida.domain.user.UserService
import org.grida.presentation.v1.user.dto.SignInRequest
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/user")
class UserController(
    private val userService: UserService
) {

    @PostMapping
    fun signIn(
        @RequestBody signInRequest: SignInRequest
    ): ApiResponse<IdResponse> {
        val id = userService.appendNormalUser(signInRequest.toUser())
        return ApiResponse.id(id)
    }
}
