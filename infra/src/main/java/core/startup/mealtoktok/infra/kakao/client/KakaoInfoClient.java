package core.startup.mealtoktok.infra.kakao.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import core.startup.mealtoktok.infra.feign.config.FeignConfig;
import core.startup.mealtoktok.infra.kakao.dto.KakaoProfileResponse;

@FeignClient(
        name = "kakao-info-client",
        url = "https://kapi.kakao.com",
        configuration = FeignConfig.class)
public interface KakaoInfoClient {

    @GetMapping("v1/oidc/userinfo")
    KakaoProfileResponse getUserInfo(@RequestHeader("Authorization") String accessToken);
}
