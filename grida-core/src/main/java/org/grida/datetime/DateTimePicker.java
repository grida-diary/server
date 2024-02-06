package org.grida.datetime;

import java.time.LocalDateTime;

public class DateTimePicker {

    public LocalDateTime now() {
        return LocalDateTime.now();
    }

    public DateTimeRange thisMonthRange() {
        LocalDateTime now = LocalDateTime.now();

        return new DateTimeRange(
                now.withDayOfMonth(1),
                now.withDayOfMonth(now.toLocalDate().lengthOfMonth())
        );
    }

    public DateTimeRange monthOfYearRange(int year, int month) {
        LocalDateTime startDate = LocalDateTime.of(year, month, 1, 0, 0);

        return new DateTimeRange(
                startDate.withDayOfMonth(1),
                startDate.withDayOfMonth(startDate.toLocalDate().lengthOfMonth())
        );
    }
}
