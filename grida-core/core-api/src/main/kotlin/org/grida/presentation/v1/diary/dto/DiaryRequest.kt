package org.grida.presentation.v1.diary.dto

import org.grida.datetime.DateTimeUtil
import org.grida.domain.diary.Diary
import org.grida.domain.diary.DiaryScope

data class DiaryRequest(
    val content: String,
    val targetDate: String,
    val scope: DiaryScope
) {

    fun toDiary(userId: Long): Diary {
        return Diary(
            userId = userId,
            content = content,
            targetDate = DateTimeUtil.toLocalDate(targetDate),
            scope = scope
        )
    }
}
