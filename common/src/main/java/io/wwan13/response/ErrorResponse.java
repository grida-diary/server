package io.wwan13.response;

import io.wwan13.exeption.base.ErrorCode;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class ErrorResponse extends ApiResponse {

    private final String code;

    private ErrorResponse(ErrorCode errorCode) {
        super(false, errorCode.getHttpStatus(), errorCode.getMessage());
        this.code = errorCode.name();
    }

    private ErrorResponse(ErrorCode errorCode, String message) {
        super(false, errorCode.getHttpStatus(), message);
        this.code = errorCode.name();
    }

    public static ErrorResponse of(ErrorCode errorCode) {
        return new ErrorResponse(errorCode);
    }

    public static ErrorResponse of(ErrorCode errorCode, String message) {
        return new ErrorResponse(errorCode, message);
    }

}
