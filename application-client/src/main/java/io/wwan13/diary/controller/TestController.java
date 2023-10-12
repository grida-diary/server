package io.wwan13.diary.controller;

import io.wwan13.constant.HttpStatusCode;
import io.wwan13.diary.usecase.CreateDiaryUseCase;
import io.wwan13.diary.usecase.ImageGenerationTestUseCase;
import io.wwan13.diary.usecase.NaturalLanguageProcessUseCase;
import io.wwan13.response.SuccessResponse;
import io.wwan13.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/test")
public class TestController {

    private final ImageGenerationTestUseCase imageGenerationTestUseCase;
    private final NaturalLanguageProcessUseCase naturalLanguageProcessUseCase;
    private final CreateDiaryUseCase createDiaryUseCase;

    @PostMapping("/test")
    public SuccessResponse<String> test(@RequestBody String content) {
        return SuccessResponse.ok(imageGenerationTestUseCase.execute(content));
    }

    @PostMapping("/test2")
    public SuccessResponse<String> test2(@RequestBody String content) {
        return SuccessResponse.ok(naturalLanguageProcessUseCase.execute(content));
    }

    @GetMapping("/test3")
    public String test3() {
        String memberEmail = SecurityUtil.getCurrentUserEmail();
        return memberEmail;
    }
}
