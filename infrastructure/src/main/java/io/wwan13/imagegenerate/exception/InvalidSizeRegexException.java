package io.wwan13.imagegenerate.exception;

import io.wwan13.exeption.base.BaseException;

import static io.wwan13.imagegenerate.exception.ImageGenerateErrorCode.INVALID_SIZE_REGEX;

public class InvalidSizeRegexException extends BaseException {

    public InvalidSizeRegexException() {
        super(INVALID_SIZE_REGEX);
    }
}
