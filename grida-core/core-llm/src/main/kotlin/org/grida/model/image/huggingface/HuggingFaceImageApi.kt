package org.grida.model.image.huggingface

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestHeader

@FeignClient(
    name = "HuggingFaceImageApi",
    url = "https://api-inference.huggingface.co/models",
)
interface HuggingFaceImageApi {

    @PostMapping("/{model}")
    fun generateImage(
        @PathVariable model: String,
        @RequestHeader("Authorization") bearerToken: String,
        request: HuggingFaceImageRequest
    ): ByteArray
}
