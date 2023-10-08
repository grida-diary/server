package io.wwan13.domain.member.entity;

import io.wwan13.common.basetime.BaseTimeEntity;
import io.wwan13.domain.member.dto.MemberProfileDto;
import io.wwan13.domain.member.entity.wrap.*;
import io.wwan13.domain.member.exception.MemberNotFountException;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Embedded
    private Email email;

    @Embedded
    private Password password;

    @Embedded
    private Nickname nickname;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Embedded
    private Age age;

    @Enumerated(EnumType.STRING)
    private Authority authority;

    @Builder
    public Member(String email, String password, String nickname, Gender gender, Integer age, Authority authority) {
        this.email = new Email(email);
        this.password = new Password(password);
        this.nickname = new Nickname(nickname);
        this.gender = gender;
        this.age = new Age(age);
        this.authority = authority;
    }

    public void updateProfile(MemberProfileDto memberProfileDto) {
        nickname.update(memberProfileDto.getNickname());
        gender = memberProfileDto.getGender();
        age.update(memberProfileDto.getAge());
    }

    public void updatePassword(String password) {
        this.password.update(password);
    }

    public String getEmailValue() {
        return email.getEmail();
    }

    public String getPasswordValue() {
        return password.getPassword();
    }

    public String getAuthorityName() {
        return authority.name();
    }

}