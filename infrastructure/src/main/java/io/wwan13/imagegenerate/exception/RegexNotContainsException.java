package io.wwan13.imagegenerate.exception;

import io.wwan13.exeption.base.BaseException;

import static io.wwan13.imagegenerate.exception.ImageGenerateErrorCode.REGEX_NOT_CONTAINS;

public class RegexNotContainsException extends BaseException {

    public RegexNotContainsException() {
        super(REGEX_NOT_CONTAINS);
    }
}
