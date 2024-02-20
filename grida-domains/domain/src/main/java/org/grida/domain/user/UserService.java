package org.grida.domain.user;

import lombok.RequiredArgsConstructor;
import org.grida.datetime.DateTimePicker;
import org.grida.exception.DomainException;
import org.springframework.stereotype.Service;

import static org.grida.exception.DomainErrorCode.ALREADY_REGISTERED_EMAIL;

@Service
@RequiredArgsConstructor
public class UserService {

    private final DateTimePicker dateTimePicker;
    private final UserRepository userRepository;

    public String join(UserAccount account) {
        if (!isUsableEmail(account.email())) {
            throw new DomainException(ALREADY_REGISTERED_EMAIL);
        }
        return userRepository.saveAccount(account, dateTimePicker.now());
    }

    public boolean needOnboarding(String email) {
        UserAppearance appearance = userRepository.findAppearanceByEmail(email);
        return appearance.isAllEmpty();
    }

    public String onboarding(String email, UserAppearance appearance) {
        userRepository.modifyAppearance(email, appearance);
        return email;
    }

    public User read(String email) {
        return userRepository.findByEmail(email);
    }

    public UserAccount readAccount(String email) {
        return userRepository.findAccountByEmail(email);
    }

    public UserRole readRole(String email) {
        return userRepository.findRoleByEmail(email);
    }

    public boolean isUsableEmail(String email) {
        return !userRepository.existByEmail(email);
    }
}
