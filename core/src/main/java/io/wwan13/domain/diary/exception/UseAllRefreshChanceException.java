package io.wwan13.domain.diary.exception;

import io.wwan13.exeption.base.BaseException;
import io.wwan13.exeption.base.ErrorCode;

public class UseAllRefreshChanceException extends BaseException {
    public UseAllRefreshChanceException() {
        super(DiaryErrorCode.USE_ALL_REFRESH_CHANCE);
    }
}
