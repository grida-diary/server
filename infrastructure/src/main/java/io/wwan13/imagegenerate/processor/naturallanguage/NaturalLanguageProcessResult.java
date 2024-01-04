package io.wwan13.imagegenerate.processor.naturallanguage;

import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class NaturalLanguageProcessResult {

    private String emotion;
    private String behavior;
}
