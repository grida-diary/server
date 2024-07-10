package org.grida.datetime

import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

const val TIME_ZONE_SEOUL = "Asia/Seoul"
const val DEFAULT_FORMAT = "yyyy-MM-dd HH:mm:ss"

fun LocalDateTime.withDefaultFormat(): String {
    return this.format(DateTimeFormatter.ofPattern(DEFAULT_FORMAT))
}

class DateTimePicker {

    fun now(): LocalDateTime {
        return ZonedDateTime.now(ZoneId.of(TIME_ZONE_SEOUL)).toLocalDateTime()
    }

    fun monthOfYearRange(
        year: Int,
        month: Int
    ): DateTimeRange {
        val targetDate = ZonedDateTime.of(year, month, 1, 0, 0, 0, 0, ZoneId.of(TIME_ZONE_SEOUL))
            .toLocalDateTime()

        return DateTimeRange(
            targetDate.withDayOfMonth(1),
            targetDate.withDayOfMonth(targetDate.toLocalDate().lengthOfMonth())
        )
    }

    companion object Util {

        private val dateTimePicker = DateTimePicker()

        fun now(): LocalDateTime {
            return dateTimePicker.now()
        }

        fun monthOfYearRange(
            year: Int,
            month: Int
        ): DateTimeRange {
            return dateTimePicker.monthOfYearRange(year, month)
        }
    }
}
