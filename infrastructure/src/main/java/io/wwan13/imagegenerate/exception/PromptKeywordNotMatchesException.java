package io.wwan13.imagegenerate.exception;

import io.wwan13.exeption.base.BaseException;

import static io.wwan13.imagegenerate.exception.ImageGenerateErrorCode.PROMPT_KEYWORDS_NOT_MATCHES;

public class PromptKeywordNotMatchesException extends BaseException {

    public PromptKeywordNotMatchesException() {
        super(PROMPT_KEYWORDS_NOT_MATCHES);
    }
}
