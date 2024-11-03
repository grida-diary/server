package org.grida.presentation.v1.profileimage

import org.grida.api.ApiResponse
import org.grida.api.dto.BooleanResultResponse
import org.grida.api.dto.IdResponse
import org.grida.support.RequestUserId
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/user/image")
class ProfileImageController(
    private val generateProfileImageUseCase: GenerateProfileImageUseCase,
    private val applyProfileImageUseCase: ApplyProfileImageUseCase,
    private val changeProfileImageUseCase: ChangeProfileImageUseCase,
    private val hasActivateProfileImageUseCase: HasActivateProfileImageUseCase,
    private val readProfileImageUseCase: ReadProfileImageUseCase,
    private val readActivateProfileImageUseCase: ReadActivateProfileImageUseCase,
    private val readProfileImageHistoryUseCase: ReadProfileImageHistoryUseCase,
) {

    @PostMapping
    fun generateSampleProfileImage(
        @RequestUserId userId: Long,
        @RequestBody request: GenerateSampleProfileImageRequest,
    ): ApiResponse<IdResponse> {
        val response = generateProfileImageUseCase.execute(userId, request)
        return ApiResponse.success(response)
    }

    @PostMapping("/apply/{profileImageId}")
    fun applyProfileImage(
        @RequestUserId userId: Long,
        @PathVariable profileImageId: Long,
    ): ApiResponse<IdResponse> {
        val response = applyProfileImageUseCase.execute(userId, profileImageId)
        return ApiResponse.success(response)
    }

    @PostMapping("/change/{profileImageId}")
    fun changeProfileImage(
        @RequestUserId userId: Long,
        @PathVariable profileImageId: Long,
    ): ApiResponse<IdResponse> {
        val response = changeProfileImageUseCase.execute(userId, profileImageId)
        return ApiResponse.success(response)
    }

    @GetMapping("/exists")
    fun hasActivateProfileImage(
        @RequestUserId userId: Long,
    ): ApiResponse<BooleanResultResponse> {
        val response = hasActivateProfileImageUseCase.execute(userId)
        return ApiResponse.success(response)
    }

    @GetMapping("/{profileImageId}")
    fun read(
        @RequestUserId userId: Long,
        @PathVariable profileImageId: Long
    ): ApiResponse<ProfileImageResponse> {
        val response = readProfileImageUseCase.execute(profileImageId)
        return ApiResponse.success(response)
    }

    @GetMapping
    fun readActivateProfileImage(
        @RequestUserId userId: Long,
    ): ApiResponse<ProfileImageResponse> {
        val response = readActivateProfileImageUseCase.execute(userId)
        return ApiResponse.success(response)
    }

    @GetMapping("/history")
    fun readProfileImageHistory(
        @RequestUserId userId: Long,
    ): ApiResponse<ProfileImageHistoryResponse> {
        val response = readProfileImageHistoryUseCase.execute(userId)
        return ApiResponse.success(response)
    }
}
