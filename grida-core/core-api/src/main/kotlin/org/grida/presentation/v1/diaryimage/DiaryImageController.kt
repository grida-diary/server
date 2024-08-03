package org.grida.presentation.v1.diaryimage

import io.wwan13.wintersecurity.resolve.RequestUserId
import org.grida.api.ApiResponse
import org.grida.api.dto.IdResponse
import org.grida.domain.diaryimage.DiaryImageService
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/diary")
class DiaryImageController(
    private val diaryImageService: DiaryImageService
) {

    @PostMapping("/{diaryId}/image")
    fun generateDiaryImage(
        @RequestUserId userId: Long,
        @PathVariable diaryId: Long
    ): ApiResponse<IdResponse> {
        val generatedDiaryImageId = diaryImageService.generateDiaryImage(diaryId, userId)
        val response = IdResponse(generatedDiaryImageId)
        return ApiResponse.success(response)
    }

    @PostMapping("/{diaryId}/image/{diaryImageId}")
    fun applyDiaryImage(
        @RequestUserId userId: Long,
        @PathVariable diaryId: Long,
        @PathVariable diaryImageId: Long
    ): ApiResponse<IdResponse> {
        diaryImageService.applyDiaryImage(diaryImageId, diaryId, userId)
        val response = IdResponse(diaryId)
        return ApiResponse.success(response)
    }

    @PostMapping("/{diaryId}/image/{diaryImageId}/change")
    fun changeDiaryImage(
        @RequestUserId userId: Long,
        @PathVariable diaryId: Long,
        @PathVariable diaryImageId: Long
    ): ApiResponse<IdResponse> {
        diaryImageService.changeDiaryImage(diaryImageId, diaryId, userId)
        val response = IdResponse(diaryId)
        return ApiResponse.success(response)
    }
}
