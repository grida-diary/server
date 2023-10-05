package io.wwan13.core.domain.member.entity.wrap;

import io.wwan13.core.domain.member.exception.AgeValueErrorException;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Getter
@NoArgsConstructor
@Embeddable
public class Age {

    private static final int AGE_MIN_VALUE = 0;

    private Integer age;

    public Age(Integer age) {
        validate(age);
        this.age = age;
    }

    public void update(Integer updateValue) {
        validate(updateValue);
        this.age = updateValue;
    }

    private void validate(Integer age) {
        if (!isRightValue(age)) {
            throw new AgeValueErrorException();
        }
    }

    private boolean isRightValue(Integer age) {
        return age > 0;
    }
}
