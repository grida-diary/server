package org.grida.domain.diary

import org.grida.domain.base.Timestamp
import java.time.LocalDate

data class Diary(
    val id: Long = 0,
    val userId: Long,
    val timestamp: Timestamp = Timestamp(),
    val targetDate: LocalDate,
    val content: String,
    val scope: DiaryScope
) {
    fun isOwner(assessorId: Long): Boolean {
        return userId == assessorId
    }
}
