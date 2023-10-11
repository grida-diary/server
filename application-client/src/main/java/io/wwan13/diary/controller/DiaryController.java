package io.wwan13.diary.controller;

import io.wwan13.diary.dto.DiaryCreateRequestDto;
import io.wwan13.diary.dto.DiaryCreateResponseDto;
import io.wwan13.diary.usecase.CreateDiaryUseCase;
import io.wwan13.response.SuccessResponse;
import io.wwan13.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/diary")
@RequiredArgsConstructor
public class DiaryController {

    private final CreateDiaryUseCase createDiaryUseCase;

    @PostMapping
    public SuccessResponse<DiaryCreateResponseDto> create(
            @RequestBody DiaryCreateRequestDto diaryCreateRequestDto ) {
        String memberEmail = SecurityUtil.getCurrentUserEmail();
        DiaryCreateResponseDto response = createDiaryUseCase.execute(diaryCreateRequestDto, memberEmail);
        return SuccessResponse.created(response);
    }
}
