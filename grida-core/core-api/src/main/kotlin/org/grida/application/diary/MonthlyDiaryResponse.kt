package org.grida.application.diary

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
