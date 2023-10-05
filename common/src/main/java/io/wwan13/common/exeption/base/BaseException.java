package io.wwan13.common.exeption.base;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public abstract class BaseException extends RuntimeException {
    private final ErrorCode errorCode;
}
