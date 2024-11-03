package org.grida.provider.profileimage

import org.springframework.stereotype.Component

@Component
class ProfileImagePromptBuilder {

    fun build(
        key: ProfileImageKey
    ): String {
        return """
            다음 글을 읽고 프로필 이미지 생성을 위한 프롬프트를 영어로 작성해줘
             
             ${key.theme}
             
            ${key.age}세 한국 ${key.gender}의 프로필 사진.
            외형은 ${key.appearance}
            
            다음 규칙은 꼭 지켜줘
            1. 생성된 이미지에는 꼭 한 명의 사람만 보여야해,
            2. 한 명의 사람 외에 글씨나 설명 같은 부가 요소는 모두 없애줘.
            2. 한 명의 사람은 정면을 응시하고 있어.
            3. 배경은 아무것도 없는 흰색 배경이야.
            5. 단정한 옷을 입고 있어
            
            생성된 프롬프트는 JSON 형식으로 반환해줘 JSON 형식은 다음과 같아.
            markdown code로 감싸지 말아줘
            {   
                "value" : "생성된 프롬프트 (STRING)"
            }
        """.trimIndent()
    }
}
