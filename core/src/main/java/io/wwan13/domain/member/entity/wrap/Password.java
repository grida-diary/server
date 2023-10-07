package io.wwan13.domain.member.entity.wrap;

import io.wwan13.domain.member.exception.IncorrectPasswordException;
import io.wwan13.domain.member.exception.PasswordSizeErrorException;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class Password {

    private static final int PASSWORD_MIN_SIZE = 4;
    private static final int PASSWORD_MAX_SIZE = 12;

    private String password;

    public Password(String password) {
        validate(password);
        this.password = password;
    }

    public void update(String updateValue) {
        validate(updateValue);
        this.password = updateValue;
    }

    public void check(String requestPassword) {
        if (!isSameValue(requestPassword)) {
            throw new IncorrectPasswordException();
        }
    }

    private void validate(String password) {
        if (!isRightSize(password)) {
            throw new PasswordSizeErrorException();
        }
    }

    private boolean isRightSize(String password) {
        int passwordSize = password.length();
        return passwordSize >= PASSWORD_MIN_SIZE || passwordSize <= PASSWORD_MAX_SIZE;
    }

    private boolean isSameValue(String requestPassword) {
        return password.equals(requestPassword);
    }
}
