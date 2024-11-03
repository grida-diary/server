package org.grida.model.image.huggingface

import com.fasterxml.jackson.annotation.JsonProperty

data class HuggingFaceImageRequest(
    val inputs: String,
    @JsonProperty("target_size")
    val targetSize: TargetSize
) {
    data class TargetSize(
        val width: Int,
        val height: Int
    )

    constructor(
        inputs: String,
        width: Int,
        height: Int
    ) : this(
        inputs,
        TargetSize(width, height)
    )
}
