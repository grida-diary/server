package org.grida.presentation.v1.diary

import io.wwan13.wintersecurity.resolve.RequestUserId
import org.grida.api.ApiResponse
import org.grida.api.dto.IdResponse
import org.grida.domain.diary.DiaryService
import org.grida.presentation.v1.diary.dto.DiaryRequest
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
}
