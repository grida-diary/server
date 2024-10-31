package org.grida.domain.diary

import org.grida.domain.base.Ownable
import org.grida.domain.base.Timestamp
import java.time.LocalDate

data class Diary(
    val id: Long = 0,
    val userId: Long,
    val timestamp: Timestamp = Timestamp(),
    val targetDate: LocalDate,
    val content: String,
    val scope: DiaryScope,
) : Ownable {

    override fun isOwner(accessorId: Long): Boolean {
        return userId == accessorId
    }

    fun isDateAfter(date: LocalDate): Boolean {
        return targetDate.isAfter(date)
    }
}
