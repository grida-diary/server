package org.grida.user

import com.fasterxml.jackson.annotation.JsonProperty

data class KakaoUserProfileResponse(
    val id: String,
    @JsonProperty("kakao_account")
    val properties: KakaoAccount
) {
    fun toKakaoUserProfile(): KakaoUserProfile {
        return KakaoUserProfile(
            id = id,
            name = properties.profile.nickname,
        )
    }
}

data class KakaoAccount(
    val profile: KakaoAccountProfile
)

data class KakaoAccountProfile(
    val nickname: String,
)
