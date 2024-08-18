package org.grida.image

data class OpenAiImageRequest(
    val prompt: String,
    val model: String,
    val n: Int,
    val size: String,
)
