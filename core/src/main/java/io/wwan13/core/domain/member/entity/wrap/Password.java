package io.wwan13.core.domain.member.entity.wrap;

import io.wwan13.core.domain.member.exception.PasswordSizeErrorException;
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

    private void validate(String password) {
        if (!isRightSize(password)) {
            throw new PasswordSizeErrorException();
        }
    }

    private boolean isRightSize(String password) {
        int passwordSize = password.length();
        return passwordSize >= PASSWORD_MIN_SIZE || passwordSize <= PASSWORD_MAX_SIZE;
    }
}
