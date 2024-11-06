package org.grida.presentation.v1

import org.grida.api.ApiResponse
import org.grida.api.dto.IdResponse
import org.grida.application.diaryimage.ApplyDiaryImageUseCase
import org.grida.application.diaryimage.ChangeDiaryImageUseCase
import org.grida.application.diaryimage.GenerateDiaryImageRequest
import org.grida.application.diaryimage.GenerateDiaryImageUseCase
import org.grida.support.RequestUserId
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/diary")
class DiaryImageController(
    private val generateDiaryImageUseCase: GenerateDiaryImageUseCase,
    private val applyDiaryImageUseCase: ApplyDiaryImageUseCase,
    private val changeDiaryImageUseCase: ChangeDiaryImageUseCase
) {

    @PostMapping("/{diaryId}/image")
    fun generateDiaryImage(
        @RequestUserId userId: Long,
        @PathVariable diaryId: Long,
        @RequestBody request: GenerateDiaryImageRequest
    ): ApiResponse<IdResponse> {
        val response = generateDiaryImageUseCase.execute(userId, diaryId, request)
        return ApiResponse.success(response)
    }

    @PostMapping("/{diaryId}/image/{diaryImageId}")
    fun applyDiaryImage(
        @RequestUserId userId: Long,
        @PathVariable diaryId: Long,
        @PathVariable diaryImageId: Long
    ): ApiResponse<IdResponse> {
        val response = applyDiaryImageUseCase.execute(userId, diaryId, diaryImageId)
        return ApiResponse.success(response)
    }

    @PostMapping("/{diaryId}/image/{diaryImageId}/change")
    fun changeDiaryImage(
        @RequestUserId userId: Long,
        @PathVariable diaryId: Long,
        @PathVariable diaryImageId: Long
    ): ApiResponse<IdResponse> {
        val response = changeDiaryImageUseCase.execute(userId, diaryId, diaryImageId)
        return ApiResponse.success(response)
    }
}
