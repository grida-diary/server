package io.wwan13.imagegenerate.exception;

import io.wwan13.exeption.base.BaseException;

import static io.wwan13.imagegenerate.exception.ImageGenerateErrorCode.REGEX_CANNOT_BE_LOWER_CASE;

public class RegexCannotBeLowerCaseException extends BaseException {

    public RegexCannotBeLowerCaseException() {
        super(REGEX_CANNOT_BE_LOWER_CASE);
    }
}
