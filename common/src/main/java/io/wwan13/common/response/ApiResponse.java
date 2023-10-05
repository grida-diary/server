package io.wwan13.common.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@ToString
@RequiredArgsConstructor
@Getter
public abstract class ApiResponse {
    private final String timeStamp = LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME);
    private final Boolean isSuccess;
    private final int status;
    private final String message;
}
