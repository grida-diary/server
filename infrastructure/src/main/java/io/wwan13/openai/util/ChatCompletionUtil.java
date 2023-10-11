package io.wwan13.openai.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.wwan13.openai.chat.dto.ChatCreateResponseDto;
import io.wwan13.openai.exception.CannotParsingResponseException;
import io.wwan13.openai.model.ProcessResult;
import org.springframework.stereotype.Component;

@Component
public class ChatCompletionUtil {

    private final static String PROMPT
            = "아래의 토픽에 대해 지금부터 작성되는 일기의 emotion 과 behavior 을 분석해서 영어로 알려줘. \n" +
            "아래의 옵션들을 지켜줘. \n\n" +
            "- Style : 키워드만 \n" +
            "- Reader level : 대학생 \n" +
            "- Perspective : 자연어 처리기 \n" +
            "- Format : JSON \n" +
            "- Just tell me the conclusion \n\n" +
            "---\n\n" +
            "\"%s\" \n" +
            "안녕 너는 내 일기를 분석해 주는 자연어 처리기야. " +
            "지금부터 나의 일기를 분석한 결과를 영어로 " +
            "\"emotion\" 과 \"behavior\" 의 key 와 각각 하나의 문자열 value 를 가지는 JSON 형태로 알려줘";

    public String createPrompt(String content) {
        String result = String.format(PROMPT, content);
        return result;
    }

    public ProcessResult getResult(ChatCreateResponseDto response)  {
        String chatResult = response.getResultMessage();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(chatResult, ProcessResult.class);
        } catch (JsonProcessingException e) {
            throw new CannotParsingResponseException();
        }
    }

}
