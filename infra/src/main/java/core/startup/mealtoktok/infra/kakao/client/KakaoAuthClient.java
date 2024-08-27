package core.startup.mealtoktok.infra.kakao.client;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import core.startup.mealtoktok.infra.feign.config.FeignConfig;
import core.startup.mealtoktok.infra.kakao.dto.KakaoOIDCPublicKeysResponse;
import core.startup.mealtoktok.infra.kakao.dto.KakaoTokenResponse;

@FeignClient(
        name = "kakao-auth-client",
        url = "https://kauth.kakao.com",
        configuration = FeignConfig.class)
public interface KakaoAuthClient {

    @PostMapping(
            "/oauth/token?grant_type=authorization_code&client_id={CLIENT_ID}&redirect_uri={REDIRECT_URI}&code={CODE}")
    KakaoTokenResponse kakaoAuth(
            @PathVariable("CLIENT_ID") String clientId,
            @PathVariable("REDIRECT_URI") String redirectUri,
            @PathVariable("CODE") String code);

    @Cacheable(cacheNames = "oidc", key = "'kakao-oidc-open-keys'", cacheManager = "cacheManager")
    @GetMapping("/.well-known/jwks.json")
    KakaoOIDCPublicKeysResponse getKakaoOIDCOpenKeys();
}
