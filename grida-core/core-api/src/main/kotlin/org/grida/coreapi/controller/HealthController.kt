package org.grida.coreapi.controller

import org.grida.api.ApiResponse
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HealthController {

    @GetMapping("/health")
    fun health(): ApiResponse {
        return ApiResponse.success()
    }
}