package org.grida.auth

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam

@FeignClient(
    name = "KakaoAuth",
    url = "https://kauth.kakao.com/oauth",
)
interface KakaoAuthApi {

    @PostMapping("/token", headers = ["Content-type=application/x-www-form-urlencoded;charset=utf-8"])
    fun provideToken(
        @RequestParam("grant_type") grantType: String,
        @RequestParam("client_id") clientId: String,
        @RequestParam("redirect_uri") redirectUri: String,
        @RequestParam("code") code: String
    ): KakaoAuthResponse

    @PostMapping("/token", headers = ["Content-type=application/x-www-form-urlencoded;charset=utf-8"])
    fun refreshToken(
        @RequestParam("grant_type") grantType: String,
        @RequestParam("client_id") clientId: String,
        @RequestParam("refresh_token") refreshToken: String
    ): KakaoAuthResponse
}
