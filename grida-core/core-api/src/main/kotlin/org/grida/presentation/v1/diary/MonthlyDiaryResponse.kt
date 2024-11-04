package org.grida.presentation.v1.diary

data class MonthlyDiaryResponse(
    val targetYear: Int,
    val targetMonth: Int,
    val diaries: Map<Int, DiaryResponse>
) {

    data class DiaryResponse(
        val diaryId: Long,
        val diaryImageUrl: String,
        val targetDate: String,
        val content: String
    )
}
