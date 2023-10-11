package io.wwan13.domain.diaryimage.exception;

import io.wwan13.exeption.base.BaseException;

public class DiaryImageNotFoundException extends BaseException {
    public DiaryImageNotFoundException() {
        super(DiaryImageErrorCode.DIARY_IMAGE_NOT_FOUND);
    }
}
