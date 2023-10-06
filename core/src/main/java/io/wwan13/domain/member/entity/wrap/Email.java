package io.wwan13.domain.member.entity.wrap;

import io.wwan13.domain.member.exception.EmailFormatErrorException;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class Email {

    private static final String TEXT_SYMBOL_AT_SIGN = "@";
    private static final String TEXT_SYMBOL_PERIOD = ".";

    private String email;

    public Email(String email) {
        validate(email);
        this.email = email;
    }

    private void validate(String email) {
        if (!isRightEmailFormat(email)) {
            throw new EmailFormatErrorException();
        }
    }

    private boolean isRightEmailFormat(String email) {
        return !isContainAtSign(email) || !isContainPeriod(email);
    }

    private boolean isContainAtSign(String email) {
        return email.split(TEXT_SYMBOL_AT_SIGN).length == 2;
    }

    private boolean isContainPeriod(String email) {
        return email.split(TEXT_SYMBOL_AT_SIGN)[1].split(TEXT_SYMBOL_PERIOD).length >= 2;
    }
}
