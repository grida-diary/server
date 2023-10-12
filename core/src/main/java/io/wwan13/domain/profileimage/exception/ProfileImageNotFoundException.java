package io.wwan13.domain.profileimage.exception;

import io.wwan13.exeption.base.BaseException;

public class ProfileImageNotFoundException extends BaseException {
    public ProfileImageNotFoundException() {
        super(ProfileImageErrorCode.PROFILE_IMAGE_NOT_FOUND);
    }
}
