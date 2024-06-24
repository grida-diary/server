package org.grida.datetime

import java.time.LocalDateTime

class DateTimePicker {

    fun now(): LocalDateTime {
        return LocalDateTime.now()
    }

    fun thisMonthRange(): DateTimeRange {
        val now = LocalDateTime.now()

        return DateTimeRange(
            now.withDayOfMonth(1),
            now.withDayOfMonth(now.toLocalDate().lengthOfMonth())
        )
    }

    fun monthOfYearRange(
        year: Int,
        month: Int
    ): DateTimeRange {
        val targetDate = LocalDateTime.of(year, month, 1, 0, 0)

        return DateTimeRange(
            targetDate.withDayOfMonth(1),
            targetDate.withDayOfMonth(targetDate.toLocalDate().lengthOfMonth())
        )
    }
}
