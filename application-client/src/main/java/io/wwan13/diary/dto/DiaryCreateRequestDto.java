package io.wwan13.diary.dto;

import io.wwan13.domain.diary.entity.Diary;
import io.wwan13.domain.diary.entity.wrap.DiaryScope;
import io.wwan13.domain.member.entity.Member;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DiaryCreateRequestDto {
    private String content;
    private DiaryScope scope;
    private LocalDate date;

    public Diary map(Member member) {
        return Diary.builder()
                .owner(member)
                .content(content)
                .scope(scope)
                .date(date)
                .build();
    }
}
