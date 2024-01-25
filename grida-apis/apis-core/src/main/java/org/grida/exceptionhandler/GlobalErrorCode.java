package org.grida.exceptionhandler;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.grida.exception.ErrorCode;

import static org.grida.http.HttpStatusCode.*;

@Getter
@RequiredArgsConstructor
public enum GlobalErrorCode implements ErrorCode {

    INVALID_HTTP_REQUEST(BAD_REQUEST, "잘못된 HTTP 요청입니다."),
    INVALID_HTTP_MESSAGE_BODY(BAD_REQUEST,"HTTP 요청 바디의 형식이 잘못되었습니다."),
    UNSUPPORTED_HTTP_METHOD(METHOD_NOT_ALLOWED,"지원하지 않는 HTTP 메서드입니다."),
    API_NOT_FOUND(NOT_FOUND, "API 를 찾을 수 없습니다."),
    SERVER_ERROR(INTERNAL_SERVER_ERROR,"서버 내부에서 알 수 없는 오류가 발생했습니다.")
    ;

    private final int httpStatus;
    private final String message;
}
