package io.wwan13.storage.exception;

import io.wwan13.exeption.base.BaseException;

import static io.wwan13.storage.exception.StorageErrorCode.CANNOT_READ_IMAGE;

public class CannotReadImageException extends BaseException {

    public CannotReadImageException() {
        super(CANNOT_READ_IMAGE);
    }
}
