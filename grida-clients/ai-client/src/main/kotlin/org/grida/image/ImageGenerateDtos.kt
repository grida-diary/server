package org.grida.image

data class ImageGenerateRequest(
    val model: String? = DEFAULT_IMAGE_GENERATE_MODEL,
    val prompt: String,
    val n: Int? = DEFAULT_IMAGE_GENERATE_N,
    val size: String? = DEFAULT_IMAGE_GENERATE_SIZE,
)

data class ImageGenerateResponse(
    val data: List<ImageUrl>,
) {
    val imageUrl: String
        get() = data[0].url

    val imageUrls: List<String>
        get() = data.map { it.url }
}

data class ImageUrl(
    val url: String,
)
