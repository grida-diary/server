package org.grida.presentation.v1.profileimage

import org.grida.api.dto.IdResponse
import org.grida.domain.profileimage.ProfileImageService
import org.grida.image.OpenAiImageClient
import org.springframework.stereotype.Component

@Component
class GenerateProfileImageUseCase(
    private val profileImageService: ProfileImageService,
    private val openAiImageClient: OpenAiImageClient,
) {

    fun execute(
        userId: Long,
        request: GenerateSampleProfileImageRequest,
    ): IdResponse {
        // val prompt = profileImageService.generateProfileImagePrompt(request.toAppearance())
        // val profileImage = openAiImageClient.generate(prompt)
        val profileImageId = profileImageService.append(userId, "image", request.appearance)
        return IdResponse(profileImageId)
    }
}
