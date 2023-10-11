package io.wwan13.domain.diary.exception;

import io.wwan13.exeption.base.BaseException;
import io.wwan13.exeption.base.ErrorCode;

public class FutureDiaryException extends BaseException {
    public FutureDiaryException() {
        super(DiaryErrorCode.FUTURE_DIARY);
    }
}
