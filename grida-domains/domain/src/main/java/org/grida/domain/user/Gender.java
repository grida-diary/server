package org.grida.domain.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.grida.exception.DomainException;

import java.util.Arrays;

import static org.grida.exception.DomainErrorCode.INVALID_GENDER;

@Getter
@RequiredArgsConstructor
public enum Gender {

    MAN("man"),
    WOMAN("woman");

    private final String value;

    public static Gender of(String gender) {
        return Arrays.stream(values())
                .filter(value -> gender.equals(value.getValue()) || gender.equals(value.name()))
                .findFirst()
                .orElseThrow(() -> new DomainException(INVALID_GENDER));
    }
}
