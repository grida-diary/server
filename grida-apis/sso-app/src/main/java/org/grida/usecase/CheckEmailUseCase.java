package org.grida.usecase;

import lombok.RequiredArgsConstructor;
import org.grida.domain.user.UserService;
import org.grida.dto.request.CheckEmailRequest;
import org.grida.dto.response.CheckEmailResponse;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CheckEmailUseCase {

    private final UserService userService;

    public CheckEmailResponse execute(CheckEmailRequest request) {
        boolean isUsable = userService.isUsableEmail(request.email());
        return new CheckEmailResponse(request.email(), isUsable);
    }
}
