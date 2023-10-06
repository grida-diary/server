package io.wwan13.domain.member.entity.wrap;

import io.wwan13.domain.member.exception.NicknameSizeErrorException;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class Nickname {

    private static final int NICKNAME_MIN_SIZE = 2;
    private static final int NICKNAME_MAX_SIZE = 12;

    private String nickname;

    public Nickname(String nickname) {
        validate(nickname);
        this.nickname = nickname;
    }

    public void update(String updateValue) {
        validate(updateValue);
        this.nickname = updateValue;
    }

    private void validate(String nickname) {
        if(!isRightSize(nickname)) {
            throw new NicknameSizeErrorException();
        }
    }

    private boolean isRightSize(String nickname) {
        int nicknameSize = nickname.length();
        return nicknameSize >= NICKNAME_MIN_SIZE || nicknameSize <= NICKNAME_MAX_SIZE;
    }
}
