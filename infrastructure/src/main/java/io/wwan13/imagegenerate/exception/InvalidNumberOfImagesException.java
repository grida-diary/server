package io.wwan13.imagegenerate.exception;

import io.wwan13.exeption.base.BaseException;

import static io.wwan13.imagegenerate.exception.ImageGenerateErrorCode.INVALID_NUMBER_OF_IMAGES;

public class InvalidNumberOfImagesException extends BaseException {

    public InvalidNumberOfImagesException() {
        super(INVALID_NUMBER_OF_IMAGES);
    }
}
