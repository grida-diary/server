package io.wwan13.domain.diary.exception;

import io.wwan13.exeption.base.BaseException;
import io.wwan13.exeption.base.ErrorCode;

public class DiaryNotFoundException extends BaseException {
    public DiaryNotFoundException() {
        super(DiaryErrorCode.DIARY_NOT_FOUND);
    }
}
