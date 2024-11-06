package org.grida.application.profileimage

import org.grida.S3Client
import org.grida.api.dto.IdResponse
import org.grida.domain.profileimage.ProfileImageService
import org.grida.domain.user.UserService
import org.grida.provider.profileimage.ProfileImageGenerator
import org.grida.provider.profileimage.ProfileImageKey
import org.springframework.stereotype.Component

@Component
class GenerateProfileImageUseCase(
    private val userService: UserService,
    private val profileImageService: ProfileImageService,
    private val profileImageGenerator: ProfileImageGenerator,
    private val s3Client: S3Client
) {

    fun execute(
        userId: Long,
        request: GenerateSampleProfileImageRequest,
    ): IdResponse {
        val user = userService.read(userId)
        val key = ProfileImageKey(user.theme, user.profile.gender.name, user.profile.age, request.appearance)

        val profileImageUrl = profileImageGenerator.generate(key)
        val uploadedUrl = s3Client.uploadImage(profileImageUrl)

        val profileImageId = profileImageService.append(userId, uploadedUrl, request.appearance)
        return IdResponse(profileImageId)
    }
}
