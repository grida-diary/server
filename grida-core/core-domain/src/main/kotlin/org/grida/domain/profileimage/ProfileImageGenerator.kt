package org.grida.domain.profileimage

import org.grida.image.ImageGenerateClient
import org.grida.image.ImageGenerateRequest
import org.springframework.stereotype.Component

@Component
class ProfileImageGenerator(
    private val imageGenerateClient: ImageGenerateClient
) {

    fun generate(
        appearance: Appearance
    ): String {
        val prompt = ProfileImagePrompt(appearance)
        val request = ImageGenerateRequest(prompt.value)
        val response = imageGenerateClient.generate(request)

        return response.imageUrl
    }
}
