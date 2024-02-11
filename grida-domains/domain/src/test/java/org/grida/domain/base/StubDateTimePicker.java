package org.grida.domain.base;

import org.grida.datetime.DateTimePicker;

import java.time.LocalDateTime;

public class StubDateTimePicker extends DateTimePicker {

    @Override
    public LocalDateTime now() {
        return LocalDateTime.MIN;
    }
}
