package org.grida.datetime;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class DateTimeUtil {

    private DateTimeUtil() {
    }

    public static Date toDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }
}
