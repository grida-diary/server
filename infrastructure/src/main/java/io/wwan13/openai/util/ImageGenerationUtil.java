package io.wwan13.openai.util;

import io.wwan13.openai.image.dto.ImageGenerateResponseDto;
import io.wwan13.openai.model.MemberProfile;
import io.wwan13.openai.model.ProcessResult;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ImageGenerationUtil {

    private final static String DIARY_PROMPT
            = "By Vladimir Kush \n\n" +
            "%s years old %s\n" +
            "doing \"%s\" with a \"%s\" feeling";

    private final static String PROFILE_PROMPT
            = "By Vladimir Kush \n\n" +
            "%s years old %s\n" +
            "profile image";

    public String createDiaryPrompt(MemberProfile memberProfile, ProcessResult processResult) {
        String result = String.format(DIARY_PROMPT, memberProfile.getAge(), memberProfile.getGender(),
                processResult.getBehavior(), processResult.getEmotion());
        return result;
    }

    public String creatProfilePrompt(MemberProfile memberProfile) {
        String result = String.format(DIARY_PROMPT, memberProfile.getAge(), memberProfile.getGender());
        return result;
    }

    public String getResult(ImageGenerateResponseDto response) {
        return response.getResultUrl();
    }

    public List<String> getResults(ImageGenerateResponseDto response) {
        return response.getResultUrls();
    }
}
