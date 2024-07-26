package org.grida.datetime

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

object DateTimeUtil {

    fun toDefaultDateTimeFormat(localDateTime: LocalDateTime): String {
        return localDateTime.format(DateTimeFormatter.ofPattern(DEFAULT_DATE_TIME_FORMAT))
    }

    fun toDefaultDateFormat(localDate: LocalDate): String {
        return localDate.format(DateTimeFormatter.ofPattern(DEFAULT_DATE_FORMAT))
    }

    fun toLocalDateTime(rawLocalDateTime: String): LocalDateTime {
        return LocalDateTime.parse(rawLocalDateTime, DateTimeFormatter.ofPattern(DEFAULT_DATE_TIME_FORMAT))
    }

    fun toLocalDate(rawLocalDate: String): LocalDate {
        return LocalDate.parse(rawLocalDate, DateTimeFormatter.ofPattern(DEFAULT_DATE_FORMAT))
    }
}
