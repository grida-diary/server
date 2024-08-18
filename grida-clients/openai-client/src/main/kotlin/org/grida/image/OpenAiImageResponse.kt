package org.grida.image

data class OpenAiImageResponse(
    val data: List<OpenAiImageUrl>,
) {
    val imageUrl: String
        get() = data[0].url

    val imageUrls: List<String>
        get() = data.map { it.url }
}

data class OpenAiImageUrl(
    val url: String,
)
