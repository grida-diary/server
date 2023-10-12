package io.wwan13.diary.controller;

import io.wwan13.diary.dto.DiaryCreateRequestDto;
import io.wwan13.diary.dto.DiaryIdResponseDto;
import io.wwan13.diary.dto.DiaryImageExampleResponseDto;
import io.wwan13.diary.usecase.CreateDiaryUseCase;
import io.wwan13.diary.usecase.GetDiaryImageExampleUseCase;
import io.wwan13.diary.usecase.SaveDiaryImageUseCase;
import io.wwan13.response.SuccessResponse;
import io.wwan13.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/diary")
@RequiredArgsConstructor
public class DiaryController {

    private final CreateDiaryUseCase createDiaryUseCase;
    private final GetDiaryImageExampleUseCase getDiaryImageExampleUseCase;
    private final SaveDiaryImageUseCase saveDiaryImageUseCase;

    @PostMapping
    public SuccessResponse<DiaryIdResponseDto> create(
            @RequestBody DiaryCreateRequestDto diaryCreateRequestDto ) {
        String memberEmail = SecurityUtil.getCurrentUserEmail();
        DiaryIdResponseDto response = createDiaryUseCase.execute(diaryCreateRequestDto, memberEmail);
        return SuccessResponse.created(response);
    }

    @GetMapping("/{diaryId}/example")
    public SuccessResponse<DiaryImageExampleResponseDto> getExampleImage(
            @PathVariable Long diaryId) {
        String memberEmail = SecurityUtil.getCurrentUserEmail();
        DiaryImageExampleResponseDto response = getDiaryImageExampleUseCase.execute(diaryId, memberEmail);
        return SuccessResponse.ok(response);
    }

    @PostMapping("/{diaryId}")
    public SuccessResponse<DiaryIdResponseDto> saveDiaryImage(
            @PathVariable Long diaryId,
            @RequestBody String diaryImageUrl) {
        DiaryIdResponseDto response = saveDiaryImageUseCase.execute(diaryId, diaryImageUrl);
        return SuccessResponse.created(response);
    }
}
