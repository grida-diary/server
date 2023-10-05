package io.wwan13.common.exeption.global;

import io.wwan13.common.constant.HttpStatusCode;
import io.wwan13.common.exeption.base.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum GlobalErrorCode implements ErrorCode {

    BAD_REQUEST_ERROR(HttpStatusCode.BAD_REQUEST, "잘못된 요청입니다."),
    INVALID_HTTP_MESSAGE_BODY(HttpStatusCode.BAD_REQUEST,"HTTP 요청 바디의 형식이 잘못되었습니다."),
    UNSUPPORTED_HTTP_METHOD(HttpStatusCode.METHOD_NOT_ALLOWED,"지원하지 않는 HTTP 메서드입니다."),
    SERVER_ERROR(HttpStatusCode.INTERNAL_SERVER_ERROR,"서버 내부에서 알 수 없는 오류가 발생했습니다.")
    ;

    private final int httpStatus;
    private final String message;

    public String getCode() {
        return this.name();
    }
}
