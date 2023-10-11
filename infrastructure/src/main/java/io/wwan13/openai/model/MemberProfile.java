package io.wwan13.openai.model;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberProfile {
    private String gender;
    private String age;
    private String profileImageUrl;

    @Builder
    public MemberProfile(String gender, Integer age, String profileImageUrl) {
        this.gender = gender;
        this.age = age.toString();
        this.profileImageUrl = profileImageUrl;
    }
}
