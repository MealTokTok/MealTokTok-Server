package core.startup.mealtoktok.infra.auth.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import core.startup.mealtoktok.infra.auth.config.KaKaoClientConfig;
import core.startup.mealtoktok.infra.auth.dto.KakaoProfileResponse;

@FeignClient(
        name = "kakao-info-client",
        url = "https://kapi.kakao.com",
        configuration = KaKaoClientConfig.class)
public interface KakaoInfoClient {

    @GetMapping("v1/oidc/userinfo")
    KakaoProfileResponse getUserInfo(@RequestHeader("Authorization") String accessToken);
}
