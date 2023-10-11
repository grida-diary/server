package io.wwan13.openai.model;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ImageDefinition {
    private String gender;
    private String age;
    private String emotion;
    private String behavior;
    private String profileImageUrl;

    @Builder
    public ImageDefinition(MemberProfile memberProfile, ProcessResult processResult) {
        this.gender = memberProfile.getGender();
        this.age = memberProfile.getAge().toString();
        this.emotion = processResult.getEmotion();
        this.behavior = processResult.getBehavior();
        this.profileImageUrl = memberProfile.getProfileImageUrl();
    }
}
