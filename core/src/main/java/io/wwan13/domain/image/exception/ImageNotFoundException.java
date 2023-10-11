package io.wwan13.domain.image.exception;

import io.wwan13.exeption.base.BaseException;
import io.wwan13.exeption.base.ErrorCode;

public class ImageNotFoundException extends BaseException {
    public ImageNotFoundException() {
        super(ImageErrorCode.IMAGE_NOT_FOUND);
    }
}
