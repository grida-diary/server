package org.grida.user

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestParam

@FeignClient(
    name = "KakaoUserApi",
    url = "https://kapi.kakao.com/v2/user",
)
interface KakaoUserApi {

    @GetMapping("/me", headers = ["Content-type=application/x-www-form-urlencoded;charset=utf-8"])
    fun readUserProfile(
        @RequestHeader("Authorization") bearerToken: String,
        @RequestParam("property_keys") propertyKeys: String,
        @RequestParam("secure_resource") secureResource: Boolean
    ): KakaoUserProfileResponse
}
