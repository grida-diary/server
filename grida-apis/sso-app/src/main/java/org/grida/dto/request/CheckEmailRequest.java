package org.grida.dto.request;

import org.grida.exception.SsoAppException;

import static org.grida.exception.SsoAppErrorCode.INVALID_EMAIL;
import static org.grida.validator.RequestValidator.required;

public record CheckEmailRequest(
        String email
) {

    public CheckEmailRequest {
        required("email", email);
        validateEmailFormat();
    }

    private void validateEmailFormat() {
        if (!email.contains("@")) {
            throw new SsoAppException(INVALID_EMAIL);
        }
    }
}
