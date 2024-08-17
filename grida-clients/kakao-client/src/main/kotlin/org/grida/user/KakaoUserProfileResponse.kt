package org.grida.user

import com.fasterxml.jackson.annotation.JsonProperty

data class KakaoUserProfileResponse(
    val id: Long,
    @JsonProperty("kakao_account")
    val properties: KakaoAccount
) {
    fun toKakaoUserProfile(): KakaoUserProfile {
        return KakaoUserProfile(
            id = id,
            name = properties.profile.nickname,
            profileImage = properties.profile.profileImage
        )
    }
}

data class KakaoAccount(
    val profile: KakaoAccountProfile
)

data class KakaoAccountProfile(
    val nickname: String,
    @JsonProperty("profile_image_url")
    val profileImage: String
)
