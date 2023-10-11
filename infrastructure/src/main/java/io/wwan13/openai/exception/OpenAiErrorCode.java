package io.wwan13.openai.exception;

import io.wwan13.constant.HttpStatusCode;
import io.wwan13.exeption.base.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum OpenAiErrorCode implements ErrorCode {

    CANNOT_PARSING_RESPONSE(HttpStatusCode.INTERNAL_SERVER_ERROR, "GPT 응답을 처리하는데 실패하였습니다.")
    ;

    private final int httpStatus;
    private final String message;
}
