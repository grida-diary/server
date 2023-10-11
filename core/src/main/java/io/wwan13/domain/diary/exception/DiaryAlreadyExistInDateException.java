package io.wwan13.domain.diary.exception;

import io.wwan13.exeption.base.BaseException;
import io.wwan13.exeption.base.ErrorCode;

public class DiaryAlreadyExistInDateException extends BaseException {
    public DiaryAlreadyExistInDateException() {
        super(DiaryErrorCode.DIARY_ALREADY_EXIST_IN_DATE);
    }
}
