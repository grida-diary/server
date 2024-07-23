package org.grida.presentation

import org.grida.api.ApiResponse
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HealthController {

    @GetMapping("/api/health")
    fun health(): ApiResponse<Any> {
        return ApiResponse.success()
    }
}
