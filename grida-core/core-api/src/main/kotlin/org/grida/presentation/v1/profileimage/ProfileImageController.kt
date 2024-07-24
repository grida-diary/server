package org.grida.presentation.v1.profileimage

import io.wwan13.wintersecurity.resolve.RequestUserId
import org.grida.api.ApiResponse
import org.grida.api.dto.BooleanResultResponse
import org.grida.api.dto.IdResponse
import org.grida.domain.profileimage.ProfileImageService
import org.grida.presentation.v1.profileimage.dto.GenerateSampleProfileImageRequest
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/user/image")
class ProfileImageController(
    private val profileImageService: ProfileImageService
) {

    @PostMapping
    fun generateSampleProfileImage(
        @RequestUserId userId: Long,
        @RequestBody request: GenerateSampleProfileImageRequest,
    ): ApiResponse<IdResponse> {
        val profileImageId = profileImageService.generateSampleProfileImage(userId, request.toAppearance())
        val response = IdResponse(profileImageId)
        return ApiResponse.success(response)
    }

    @PostMapping("/apply/{profileImageId}")
    fun applyProfileImage(
        @RequestUserId userId: Long,
        @PathVariable profileImageId: Long
    ): ApiResponse<IdResponse> {
        profileImageService.applyProfileImage(userId, profileImageId)
        val response = IdResponse(userId)
        return ApiResponse.success(response)
    }

    @PostMapping("/change/{profileImageId}")
    fun changeProfileImage(
        @RequestUserId userId: Long,
        @PathVariable profileImageId: Long
    ): ApiResponse<IdResponse> {
        profileImageService.changeProfileImage(userId, profileImageId)
        return ApiResponse.success(IdResponse(userId))
    }

    @GetMapping("/exists")
    fun hasActivateProfileImage(
        @RequestUserId userId: Long
    ): ApiResponse<BooleanResultResponse> {
        val result = profileImageService.hasActivateProfileImage(userId)
        val response = BooleanResultResponse(result)
        return ApiResponse.success(response)
    }
}
