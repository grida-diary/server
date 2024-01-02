package io.wwan13.imagegenerate.exception;

import io.wwan13.exeption.base.BaseException;

import static io.wwan13.imagegenerate.exception.ImageGenerateErrorCode.INVALID_PREFIX_REGEX;

public class InvalidPrefixRegexException extends BaseException {

    public InvalidPrefixRegexException() {
        super(INVALID_PREFIX_REGEX);
    }
}
