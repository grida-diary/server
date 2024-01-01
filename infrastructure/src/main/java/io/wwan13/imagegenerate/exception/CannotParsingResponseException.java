package io.wwan13.imagegenerate.exception;

import io.wwan13.exeption.base.BaseException;

import static io.wwan13.imagegenerate.exception.ImageGenerateErrorCode.CANNOT_PARSING_RESPONSE;

public class CannotParsingResponseException extends BaseException {

    public CannotParsingResponseException() {
        super(CANNOT_PARSING_RESPONSE);
    }
}
