package io.wwan13.domain.member.dto;

import io.wwan13.domain.member.entity.wrap.Gender;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberProfileDto {
    private String nickname;
    private Gender gender;
    private Integer age;
}
