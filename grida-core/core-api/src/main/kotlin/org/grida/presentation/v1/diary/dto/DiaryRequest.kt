package org.grida.presentation.v1.diary.dto

import org.grida.domain.diary.Diary
import org.grida.domain.diary.DiaryScope
import org.springframework.format.annotation.DateTimeFormat
import java.time.LocalDate

data class DiaryRequest(
    val content: String,
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    val targetDate: LocalDate,
    val scope: DiaryScope
) {

    fun toDiary(userId: Long): Diary {
        return Diary(
            userId = userId,
            content = content,
            targetDate = targetDate,
            scope = scope
        )
    }
}
