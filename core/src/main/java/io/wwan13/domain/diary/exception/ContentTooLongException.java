package io.wwan13.domain.diary.exception;

import io.wwan13.exeption.base.BaseException;
import io.wwan13.exeption.base.ErrorCode;

public class ContentTooLongException extends BaseException {
    public ContentTooLongException() {
        super(DiaryErrorCode.CONTENT_TOO_LONG);
    }
}
