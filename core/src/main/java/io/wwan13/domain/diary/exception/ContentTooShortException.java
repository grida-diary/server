package io.wwan13.domain.diary.exception;

import io.wwan13.exeption.base.BaseException;
import io.wwan13.exeption.base.ErrorCode;

public class ContentTooShortException extends BaseException {
    public ContentTooShortException() {
        super(DiaryErrorCode.CONTENT_TOO_SHORT);
    }
}
