package org.grida.user

import org.springframework.stereotype.Component

@Component
class KakaoUserClient(
    private val kakapUserApi: KakaoUserApi
) {

    fun readUserProfile(accessToken: String): KakaoUserProfile {
        val response = kakapUserApi.readUserProfile(
            bearerToken = "Bearer $accessToken",
            propertyKeys = QUERY_PROPERTY_KEYS,
            secureResource = true
        )

        return response.toKakaoUserProfile()
    }

    companion object {
        const val QUERY_PROPERTY_KEYS = "[\"kakao_account.profile\"]"
    }
}
