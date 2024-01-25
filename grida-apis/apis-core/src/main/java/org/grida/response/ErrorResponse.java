package org.grida.response;

import lombok.Getter;

@Getter
public class ErrorResponse extends ApiResponse {

    private final String cause;
    private final String errorCode;

    protected ErrorResponse(String cause, String errorCode, String message) {
        super(ApiStatus.ERROR, message);
        this.cause = cause;
        this.errorCode = errorCode;
    }
}
