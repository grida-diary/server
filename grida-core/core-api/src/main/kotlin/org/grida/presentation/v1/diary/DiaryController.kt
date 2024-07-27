package org.grida.presentation.v1.diary

import io.wwan13.wintersecurity.resolve.RequestUserId
import org.grida.api.ApiResponse
import org.grida.api.dto.IdResponse
import org.grida.domain.diary.DiaryService
import org.grida.presentation.v1.diary.dto.DiaryRequest
import org.grida.presentation.v1.diary.dto.DiaryResponse
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/diary")
class DiaryController(
    private val diaryService: DiaryService
) {

    @PostMapping
    fun appendDiary(
        @RequestUserId userId: Long,
        @RequestBody request: DiaryRequest
    ): ApiResponse<IdResponse> {
        val diaryId = diaryService.appendDiary(request.toDiary(userId))
        val response = IdResponse(diaryId)
        return ApiResponse.success(response)
    }

    @GetMapping("/{diaryId}")
    fun readDiary(
        @RequestUserId userId: Long,
        @PathVariable diaryId: Long
    ): ApiResponse<DiaryResponse> {
        val diary = diaryService.readDiary(diaryId, userId)
        val response = DiaryResponse.from(diary)
        return ApiResponse.success(response)
    }
}
