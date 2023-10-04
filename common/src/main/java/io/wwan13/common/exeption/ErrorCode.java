package io.wwan13.common.exeption;

import lombok.AllArgsConstructor;
import lombok.Getter;

public interface ErrorCode {
    int getHttpStatus();
    String getMessage();
    String getCode();
}
