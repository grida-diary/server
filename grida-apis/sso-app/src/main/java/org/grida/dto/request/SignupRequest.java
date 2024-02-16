package org.grida.dto.request;

import org.grida.domain.user.Gender;
import org.grida.domain.user.UserAccount;
import org.grida.domain.user.UserProfile;
import org.grida.domain.user.UserRole;
import org.grida.passwordencoder.PasswordEncoder;

import static org.grida.validator.RequestValidator.isGreaterThan;
import static org.grida.validator.RequestValidator.required;

public record SignupRequest(
        String email,
        String password,
        String passwordCheck,
        String nickname,
        int age,
        String gender
) {

    public SignupRequest {
        required("email", email);
        required("password", password);
        required("passwordCheck", passwordCheck);
        required("nickname", nickname);
        isGreaterThan("age", age, 1);
        required("gender", gender);
    }

    public UserAccount toAccount(UserRole role, PasswordEncoder passwordEncoder) {
        return new UserAccount(role, email, passwordEncoder.encode(password));
    }

    public UserProfile toProfile() {
        return new UserProfile(nickname, age, Gender.of(gender));
    }
}
