package core.startup.mealtoktok.infra.auth.client;

import core.startup.mealtoktok.infra.auth.config.KaKaoClientConfig;
import core.startup.mealtoktok.infra.auth.dto.KakaoTokenResponse;
import core.startup.mealtoktok.infra.auth.dto.KakaoOIDCPublicKeysResponse;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(
        name = "kakao-auth-client",
        url = "https://kauth.kakao.com",
        configuration = KaKaoClientConfig.class
)
public interface KakaoAuthClient {

    @PostMapping("/oauth/token?grant_type=authorization_code&client_id={CLIENT_ID}&redirect_uri={REDIRECT_URI}&code={CODE}")
    KakaoTokenResponse kakaoAuth(
            @PathVariable("CLIENT_ID") String clientId,
            @PathVariable("REDIRECT_URI") String redirectUri,
            @PathVariable("CODE") String code);

    @Cacheable(cacheNames = "oidc", key = "'kakao-oidc-open-keys'", cacheManager = "oidcCacheManager")
    @GetMapping("/.well-known/jwks.json")
    KakaoOIDCPublicKeysResponse getKakaoOIDCOpenKeys();

}
