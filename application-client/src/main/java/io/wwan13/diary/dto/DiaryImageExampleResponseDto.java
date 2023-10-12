package io.wwan13.diary.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class DiaryImageExampleResponseDto {
    private String exampleUrl;
    private Integer refreshLeft;
}
