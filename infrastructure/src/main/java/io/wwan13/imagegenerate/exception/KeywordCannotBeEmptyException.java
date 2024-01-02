package io.wwan13.imagegenerate.exception;

import io.wwan13.exeption.base.BaseException;

import static io.wwan13.imagegenerate.exception.ImageGenerateErrorCode.KEYWORD_CANNOT_BE_EMPTY;

public class KeywordCannotBeEmptyException extends BaseException {

    public KeywordCannotBeEmptyException() {
        super(KEYWORD_CANNOT_BE_EMPTY);
    }
}
