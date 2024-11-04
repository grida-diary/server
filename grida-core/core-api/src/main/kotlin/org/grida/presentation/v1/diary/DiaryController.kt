package org.grida.presentation.v1.diary

import org.grida.api.ApiResponse
import org.grida.api.dto.IdResponse
import org.grida.support.RequestUserId
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
    private val analyzeDiaryUseCase: AnalyzeDiaryUseCase,
    private val readDiaryUseCase: ReadDiaryUseCase,
    private val readMonthlyDiaryUseCase: ReadMonthlyDiaryUseCase,
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

    @PostMapping("/{diaryId}/analyze")
    fun appendDiary(
        @RequestUserId userId: Long,
        @PathVariable diaryId: Long,
    ): ApiResponse<AnalyzeDiaryResponse> {
        val response = analyzeDiaryUseCase.execute(userId, diaryId)
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

    @GetMapping("/monthly/{year}/{month}")
    fun readMonthlyDiaries(
        @RequestUserId userId: Long,
        @PathVariable year: Int,
        @PathVariable month: Int,
    ): ApiResponse<MonthlyDiaryResponse> {
        val response = readMonthlyDiaryUseCase.execute(userId, year, month)
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
