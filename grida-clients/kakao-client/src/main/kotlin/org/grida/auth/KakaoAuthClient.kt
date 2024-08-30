package org.grida.auth

import feign.FeignException
import org.grida.config.KakaoProperties
import org.springframework.stereotype.Component

@Component
class KakaoAuthClient(
    private val kakaoAuthApi: KakaoAuthApi,
    private val kakaoProperties: KakaoProperties
) {

    fun provideAuthToken(
        code: String,
        redirectUri: String
    ): KakaoAuthToken {
        try {
            val response = kakaoAuthApi.provideToken(
                grantType = "authorization_code",
                clientId = kakaoProperties.appKey,
                redirectUri = redirectUri,
                code = code
            )
            return response.toKakaoAuthToken()
        } catch (e: FeignException) {
            throw IllegalArgumentException(e.stackTraceToString())
        }
    }

    fun refreshToken(refreshToken: String): KakaoAuthToken {
        try {
            val response = kakaoAuthApi.refreshToken(
                grantType = "refresh_token",
                clientId = kakaoProperties.appKey,
                refreshToken = refreshToken
            )
            return response.toKakaoAuthToken()
        } catch (e: FeignException) {
            throw IllegalArgumentException(e.stackTraceToString())
        }
    }
}
