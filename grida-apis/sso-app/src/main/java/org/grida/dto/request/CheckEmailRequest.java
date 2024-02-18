package org.grida.dto.request;

import org.grida.exception.SsoAppException;

import static org.grida.exception.SsoAppErrorCode.INVALID_EMAIL;
import static org.grida.validator.RequestValidator.required;

public record CheckEmailRequest(
        String email
) {

    private static final String EMAIL_ADDRESS_DIVIDER = "@";

    public CheckEmailRequest {
        required("email", email);
        validateEmailFormat(email);
    }

    private void validateEmailFormat(String email) {
        if (!email.contains(EMAIL_ADDRESS_DIVIDER)) {
            throw new SsoAppException(INVALID_EMAIL);
        }
    }
}
