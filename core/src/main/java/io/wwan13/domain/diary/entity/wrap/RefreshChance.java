package io.wwan13.domain.diary.entity.wrap;

import io.wwan13.domain.diary.exception.UseAllRefreshChanceException;
import io.wwan13.domain.member.entity.wrap.Authority;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class RefreshChance {

    private final static int REFRESH_CHANCE_MIN_VALUE = 0;

    private Integer refreshChance;

    public RefreshChance(Authority authority) {
        validate(refreshChance);
        this.refreshChance = authority.getRefreshChance();
    }

    public Integer refresh() {
        validate(refreshChance);
        refreshChance -= 1;
        return refreshChance;
    }

    private void validate(Integer refreshChance) {
        if (!isOverThanMinValue()) {
            throw new UseAllRefreshChanceException();
        }
    }

    private boolean isOverThanMinValue() {
        return refreshChance >= REFRESH_CHANCE_MIN_VALUE;
    }
}
