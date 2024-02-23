package org.grida.user.dto.request;

import org.springframework.web.method.HandlerMethod;

import static org.grida.validator.RequestValidator.required;

public record ChangeActivateImageRequest(
        Long targetImageId
) {

    public ChangeActivateImageRequest {
        required("targetImageId", targetImageId);
    }
}
