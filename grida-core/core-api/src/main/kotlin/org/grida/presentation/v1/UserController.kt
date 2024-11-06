package org.grida.presentation.v1

import org.grida.api.ApiResponse
import org.grida.api.dto.IdResponse
import org.grida.application.user.ReadUserResponse
import org.grida.application.user.ReadUserUseCase
import org.grida.application.user.UpdateProfileRequest
import org.grida.application.user.UpdateProfileUseCase
import org.grida.application.user.UpdateThemeRequest
import org.grida.application.user.UpdateThemeUseCase
import org.grida.support.RequestUserId
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/user")
class UserController(
    private val readUserUseCase: ReadUserUseCase,
    private val updateProfileUseCase: UpdateProfileUseCase,
    private val updateThemeUseCase: UpdateThemeUseCase
) {

    @GetMapping
    fun readUser(
        @RequestUserId userId: Long
    ): ApiResponse<ReadUserResponse> {
        val response = readUserUseCase.execute(userId)
        return ApiResponse.success(response)
    }

    @PostMapping("/profile")
    fun updateProfile(
        @RequestUserId userId: Long,
        @RequestBody request: UpdateProfileRequest
    ): ApiResponse<IdResponse> {
        updateProfileUseCase.execute(userId, request)
        val response = IdResponse(userId)

        return ApiResponse.success(response)
    }

    @PostMapping("/theme")
    fun updateTheme(
        @RequestUserId userId: Long,
        @RequestBody request: UpdateThemeRequest
    ): ApiResponse<IdResponse> {
        updateThemeUseCase.execute(userId, request)
        val response = IdResponse(userId)

        return ApiResponse.success(response)
    }
}
