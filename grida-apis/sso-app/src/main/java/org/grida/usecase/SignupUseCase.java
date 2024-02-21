package org.grida.usecase;

import lombok.RequiredArgsConstructor;
import org.grida.domain.user.UserRole;
import org.grida.domain.user.UserService;
import org.grida.dto.UserEmailResponse;
import org.grida.dto.request.SignupRequest;
import org.grida.exception.SsoAppException;
import org.grida.passwordencoder.PasswordEncoder;
import org.springframework.stereotype.Component;

import static org.grida.exception.SsoAppErrorCode.PASSWORD_CHECK_NOT_MATCHES;

@Component
@RequiredArgsConstructor
public class SignupUseCase {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public UserEmailResponse execute(SignupRequest request) {
        validatePasswordCheck(request);

        String email = userService.join(request.toAccount(UserRole.ROLE_BASIC_USER, passwordEncoder));

        return new UserEmailResponse(email);
    }

    public void validatePasswordCheck(SignupRequest request) {
        if (!request.password().equals(request.passwordCheck())) {
            throw new SsoAppException(PASSWORD_CHECK_NOT_MATCHES);
        }
    }
}
