package io.wwan13.storage.exception;

import io.wwan13.exeption.base.BaseException;

import static io.wwan13.storage.exception.StorageErrorCode.WRONG_IMAGE_URL;

public class WrongImageUrlException extends BaseException {

    public WrongImageUrlException() {
        super(WRONG_IMAGE_URL);
    }
}
