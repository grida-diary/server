package org.grida.response;

import lombok.Getter;
import org.springframework.lang.Nullable;

@Getter
public class SuccessResponse<T> extends ApiResponse {

    @Nullable
    private final T data;

    protected SuccessResponse(T data, String message) {
        super(ApiStatus.SUCCESS, message);
        this.data = data;
    }
}
