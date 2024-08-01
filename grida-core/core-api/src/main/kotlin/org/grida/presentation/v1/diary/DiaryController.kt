package org.grida.presentation.v1.diary

import io.wwan13.wintersecurity.resolve.RequestUserId
import org.grida.api.ApiResponse
import org.grida.api.dto.IdResponse
import org.grida.domain.diary.DiaryScope
import org.grida.domain.diary.DiaryService
import org.grida.domain.diaryimage.DiaryImageService
import org.grida.presentation.v1.diary.dto.DiaryModifyRequest
import org.grida.presentation.v1.diary.dto.DiaryRequest
import org.grida.presentation.v1.diary.dto.DiaryResponse
import org.grida.presentation.v1.diary.dto.DiaryScopeRequest
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/diary")
class DiaryController(
    private val diaryService: DiaryService,
    private val diaryImageService: DiaryImageService
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
        val remainCount = diaryImageService.countRemainImageGenerateAttempt(diaryId)
        val response = DiaryResponse.from(diary, remainCount)
        return ApiResponse.success(response)
    }

    @PatchMapping("/{diaryId}")
    fun modifyDiary(
        @RequestUserId userId: Long,
        @PathVariable diaryId: Long,
        @RequestBody request: DiaryModifyRequest
    ): ApiResponse<IdResponse> {
        val modifiedDiaryId = diaryService.modify(
            diaryId,
            userId,
            request.content,
            DiaryScope.valueOf(request.scope)
        )
        val response = IdResponse(modifiedDiaryId)
        return ApiResponse.success(response)
    }

    @PatchMapping("/{diaryId}/scope")
    fun modifyDiaryScope(
        @RequestUserId userId: Long,
        @PathVariable diaryId: Long,
        @RequestBody request: DiaryScopeRequest
    ): ApiResponse<IdResponse> {
        val modifiedDiaryId = diaryService.modifyScope(
            diaryId,
            userId,
            DiaryScope.valueOf(request.scope)
        )
        val response = IdResponse(modifiedDiaryId)
        return ApiResponse.success(response)
    }
}
