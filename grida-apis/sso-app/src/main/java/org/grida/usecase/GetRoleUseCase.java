package org.grida.usecase;

import lombok.RequiredArgsConstructor;
import org.grida.domain.user.UserRole;
import org.grida.domain.user.UserService;
import org.grida.dto.response.GetRoleResponse;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GetRoleUseCase {

    private final UserService userService;

    public GetRoleResponse execute(String userEmail) {
        UserRole role = userService.readRole(userEmail);
        return new GetRoleResponse(role.name());
    }
}
