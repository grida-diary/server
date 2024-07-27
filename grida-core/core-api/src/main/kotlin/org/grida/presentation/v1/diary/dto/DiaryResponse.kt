package org.grida.presentation.v1.diary.dto

import org.grida.datetime.DateTimeUtil
import org.grida.domain.diary.Diary

data class DiaryResponse(
    val content: String,
    val targetDate: String,
    val scope: String,
    val createdAt: String
) {

    companion object {
        fun from(diary: Diary): DiaryResponse {
            return DiaryResponse(
                content = diary.content,
                targetDate = DateTimeUtil.toDefaultDateFormat(diary.targetDate),
                scope = diary.scope.name,
                createdAt = DateTimeUtil.toDefaultDateTimeFormat(diary.timestamp.createdAt)
            )
        }
    }
}
