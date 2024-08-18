package org.grida.image

import org.springframework.stereotype.Component

@Component
class OpenAiImageClient(
    private val openaiImageApi: OpenAiImageApi,
    private val openAiSecretKey: String,
) {

    fun generate(prompt: String): String {
        val request = OpenAiImageRequest(
            prompt = prompt,
            model = DEFAULT_IMAGE_GENERATE_MODEL,
            n = DEFAULT_IMAGE_GENERATE_N,
            size = DEFAULT_IMAGE_GENERATE_SIZE
        )

        val response = openaiImageApi.generate(openAiSecretKey, request)
        return response.imageUrl
    }

    companion object {
        private const val DEFAULT_IMAGE_GENERATE_MODEL = "dall-e-3"
        private const val DEFAULT_IMAGE_GENERATE_N = 1
        private const val DEFAULT_IMAGE_GENERATE_SIZE = "1024x1024"
    }
}
