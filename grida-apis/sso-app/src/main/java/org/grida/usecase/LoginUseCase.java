package org.grida.usecase;

import lombok.RequiredArgsConstructor;
import org.grida.domain.user.UserAccount;
import org.grida.domain.user.UserService;
import org.grida.dto.request.LoginRequest;
import org.grida.dto.response.LoginResponse;
import org.grida.exception.SsoAppException;
import org.grida.jwt.TokenClaims;
import org.grida.jwt.TokenGenerator;
import org.grida.jwt.TokenType;
import org.grida.passwordencoder.PasswordEncoder;
import org.springframework.stereotype.Component;

import static org.grida.exception.SsoAppErrorCode.PASSWORD_NOT_MATCHES;

@Component
@RequiredArgsConstructor
public class LoginUseCase {

    private final UserService userService;
    private final TokenGenerator tokenGenerator;
    private final PasswordEncoder passwordEncoder;

    public LoginResponse execute(LoginRequest request) {
        UserAccount account = userService.readAccount(request.email());

        validatePasswordMatches(request.password(), account.password());

        TokenClaims claims = new TokenClaims(account.email(), account.role().name());
        return new LoginResponse(
                tokenGenerator.generate(TokenType.ACCESS_TOKEN, claims),
                tokenGenerator.generate(TokenType.REFRESH_TOKEN, claims),
                userService.needOnboarding(request.email())
        );
    }

    private void validatePasswordMatches(String request, String registered) {
        if (!passwordEncoder.matches(request, registered)) {
            throw new SsoAppException(PASSWORD_NOT_MATCHES);
        }
    }
}
