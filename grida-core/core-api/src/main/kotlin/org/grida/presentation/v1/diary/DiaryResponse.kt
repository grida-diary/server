package org.grida.presentation.v1.diary

data class DiaryResponse(
    val content: String,
    val targetDate: String,
    val scope: String,
    val createdAt: String,
    val remainAttempt: Long,
    val diaryImageUrl: String,
)
