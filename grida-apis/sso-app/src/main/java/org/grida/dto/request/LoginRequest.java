package org.grida.dto.request;

import static org.grida.validator.RequestValidator.required;

public record LoginRequest(
        String email,
        String password
) {

    public LoginRequest {
        required("email", email);
        required("password", password);
    }
}
