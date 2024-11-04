package org.grida.provider.diaryimage

import org.springframework.stereotype.Component

@Component
class DiaryAnalyzePromptBuilder {

    fun build(
        key: DiaryImageKey,
    ): String {
        return """
            다음 일기를 읽고 주인공이 오늘 하루 느낀 감정과 행동이 가장 잘 드러나는
            이미지 생성을 위한 프롬프트를 영어로 작성해줘,
            
            또 이 일기를 보고 너가 주인공에게 해주고 싶은 위로의 한마디를 한글로 작성해줘.
            위로의 한마디는 나의 행동과 감정이 나타나게 작성해줘
            
            ${key.diary}
            
            주인공은 ${key.age}세 한국 ${key.gender} 이야.
            
            다음 규칙은 꼭 지켜줘
            1. ${key.theme}
            2. 반드시 이미지 생성을 위한 프롬프느는 '영어'로, 위로의 한마디는 '한글'로 작성해줘
            3. 여러가지 행동이 있다면 가장 주된 행동으로 그려줘
            4. 여러가지 감정이 보인다면 가장 잘 느껴지는 감정으로 그려줘
            5. 최대한 주인공의 감정이 잘 느껴지도록 그려줘
            
            생성된 프롬프트는 JSON 형식으로 반환해줘 JSON 형식은 다음과 같아.
            markdown code로 감싸지 말아줘
            {   
                "value" : "생성된 프롬프트 (STRING)",
                "comment" : "위로의 한마디 (STRING)"
            }
        """.trimIndent()
    }
}
