package org.grida.response;

import lombok.Getter;
import org.grida.dto.CreatedDto;
import org.grida.exception.ErrorCode;
import org.springframework.lang.Nullable;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
public abstract class ApiResponse {

    private static final String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
    private static final String EMPTY_MESSAGE = "";
    private static final String EMPTY_DATA = null;

    private final String timeStamp;
    private final ApiStatus status;
    @Nullable
    private final String message;

    protected ApiResponse(ApiStatus status, String message) {
        this.timeStamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern(DATE_TIME_PATTERN));
        this.status = status;
        this.message = message;
    }

    public static <T> ApiResponse ok(T data, String message) {
        return new SuccessResponse<>(data, message);
    }

    public static <T> ApiResponse ok(String message) {
        return new SuccessResponse<>(EMPTY_DATA, message);
    }

    public static <T> ApiResponse ok(T data) {
        return new SuccessResponse<>(data, EMPTY_MESSAGE);
    }

    public static ApiResponse created(long id, String message) {
        return new SuccessResponse<>(new CreatedDto(id), message);
    }

    public static ApiResponse created(long id) {
        return new SuccessResponse<>(new CreatedDto(id), EMPTY_MESSAGE);
    }

    public static ApiResponse error(ErrorCode errorCode, String cause) {
        return new ErrorResponse(cause, errorCode.name(), errorCode.getMessage());
    }

    public static ApiResponse error(String cause, String errorCode, String message) {
        return new ErrorResponse(cause, errorCode, message);
    }
}
