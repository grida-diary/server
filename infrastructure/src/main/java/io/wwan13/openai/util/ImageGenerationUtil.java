package io.wwan13.openai.util;

import io.wwan13.openai.image.dto.ImageGenerateResponseDto;
import io.wwan13.openai.model.MemberProfile;
import io.wwan13.openai.model.ProcessResult;
import org.springframework.stereotype.Component;

@Component
public class ImageGenerationUtil {

    private final static String prompt
            = "By Vladimir Kush \n\n" +
            "%s years old %s\n" +
            "doing \"%s\" with a \"%s\" feeling";

    public String createPrompt(MemberProfile memberProfile, ProcessResult processResult) {
        String result = String.format(prompt, memberProfile.getAge(), memberProfile.getGender(),
                processResult.getBehavior(), processResult.getEmotion());
        return result;
    }

    public String getResult(ImageGenerateResponseDto response) {
        return response.getResultUrl();
    }
}
