package io.wwan13.auth.dto;

import io.wwan13.domain.member.entity.Member;
import io.wwan13.domain.member.entity.wrap.Authority;
import io.wwan13.domain.member.entity.wrap.Gender;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SignUpRequestDto {

    private String email;
    private String password;
    private String nickname;
    private Gender gender;
    private Integer age;

    public Member map() {
        return Member.builder()
                .email(email)
                .password(password)
                .nickname(nickname)
                .gender(gender)
                .age(age)
                .authority(Authority.ROLE_USER)
                .build();
    }
}
