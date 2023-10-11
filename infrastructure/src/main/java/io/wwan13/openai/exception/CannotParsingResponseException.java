package io.wwan13.openai.exception;

import io.wwan13.exeption.base.BaseException;
import io.wwan13.exeption.base.ErrorCode;

public class CannotParsingResponseException extends BaseException {
    public CannotParsingResponseException() {
        super(OpenAiErrorCode.CANNOT_PARSING_RESPONSE);
    }
}
