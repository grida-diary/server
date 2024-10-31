package org.grida.presentation.v1.diary

import io.wwan13.wintersecurity.resolve.RequestUserId
import org.grida.api.ApiResponse
import org.grida.api.dto.IdResponse
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
    private val appendDiaryUseCase: AppendDiaryUseCase,
    private val readDiaryUseCase: ReadDiaryUseCase,
    private val modifyDiaryUseCase: ModifyDiaryUseCase,
) {

    @PostMapping
    fun appendDiary(
        @RequestUserId userId: Long,
        @RequestBody request: DiaryRequest,
    ): ApiResponse<IdResponse> {
        val response = appendDiaryUseCase.execute(userId, request)
        return ApiResponse.success(response)
    }

    @GetMapping("/{diaryId}")
    fun readDiary(
        @RequestUserId userId: Long,
        @PathVariable diaryId: Long,
    ): ApiResponse<DiaryResponse> {
        val response = readDiaryUseCase.execute(userId, diaryId)
        return ApiResponse.success(response)
    }

    @PatchMapping("/{diaryId}")
    fun modifyDiary(
        @RequestUserId userId: Long,
        @PathVariable diaryId: Long,
        @RequestBody request: DiaryModifyRequest,
    ): ApiResponse<IdResponse> {
        val response = modifyDiaryUseCase.execute(userId, diaryId, request)
        return ApiResponse.success(response)
    }
}
