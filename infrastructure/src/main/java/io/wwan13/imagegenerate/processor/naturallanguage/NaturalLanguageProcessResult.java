package io.wwan13.imagegenerate.processor.naturallanguage;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class NaturalLanguageProcessResult {

    private String emotion;
    private String behavior;
}
