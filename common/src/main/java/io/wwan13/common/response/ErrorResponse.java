package io.wwan13.common.response;

import io.wwan13.common.exeption.ErrorCode;
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

    public static ErrorResponse of(ErrorCode errorCode) {
        return new ErrorResponse(errorCode);
    }
}
