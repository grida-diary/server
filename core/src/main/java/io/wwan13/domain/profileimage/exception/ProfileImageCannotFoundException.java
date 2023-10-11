package io.wwan13.domain.profileimage.exception;

import io.wwan13.exeption.base.BaseException;
import io.wwan13.exeption.base.ErrorCode;

public class ProfileImageCannotFoundException extends BaseException {
    public ProfileImageCannotFoundException() {
        super(ProfileImageErrorCode.PROFILE_IMAGE_CANNOT_FOUND);
    }
}
