package org.grida.model.image

import org.springframework.ai.image.ImagePrompt
import org.springframework.ai.openai.OpenAiImageModel
import org.springframework.ai.openai.OpenAiImageOptions
import org.springframework.stereotype.Component

@Component
class ImageModel(
    private val openAiImageModel: OpenAiImageModel
) {

    fun call(content: String): String {
        val prompt = ImagePrompt(
            content,
            OpenAiImageOptions.builder()
                .withModel("dall-e-3")
                .withWidth(1024)
                .withHeight(1024)
                .build()
        )
        val response = openAiImageModel.call(prompt)

        return response.result.output.url!!
    }
}
