package io.wwan13.openai.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProcessResult {
    private String emotion;
    private String behavior;
}
