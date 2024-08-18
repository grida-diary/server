package org.grida.domain.profileimage

import org.grida.image.OpenAiImageClient
import org.springframework.stereotype.Component

@Component
class ProfileImageGenerator(
    private val openAiImageClient: OpenAiImageClient
) {

    fun generate(
        appearance: Appearance
    ): String {
        val prompt = ProfileImagePrompt(appearance)
        val imageUrl = openAiImageClient.generate(prompt.apply())

        return imageUrl
    }
}
