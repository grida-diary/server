package org.grida.dto.request;

import org.grida.domain.user.UserAccount;
import org.grida.domain.user.UserRole;
import org.grida.passwordencoder.PasswordEncoder;

import static org.grida.validator.RequestValidator.required;

public record SignupRequest(
        String email,
        String password,
        String passwordCheck,
        String nickname
) {

    public SignupRequest {
        required("email", email);
        required("password", password);
        required("passwordCheck", passwordCheck);
        required("nickname", nickname);
    }

    public UserAccount toAccount(UserRole role, PasswordEncoder passwordEncoder) {
        return new UserAccount(role, email, passwordEncoder.encode(password), nickname);
    }
}
