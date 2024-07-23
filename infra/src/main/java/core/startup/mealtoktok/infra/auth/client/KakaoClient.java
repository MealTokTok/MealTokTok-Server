package core.startup.mealtoktok.infra.auth.client;

import core.startup.mealtoktok.domain.auth.*;
import core.startup.mealtoktok.infra.auth.dto.KakaoTokenResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

import static core.startup.mealtoktok.common.consts.MealTokTokConstant.BEARER;


@Component
@RequiredArgsConstructor
public class KakaoClient implements OAuthClient {

    private final KakaoAuthClient kakaoAuthClient;
    private final KakaoInfoClient kakaoInfoClient;

    @Override
    public OAuthTokens auth(String clientId, String redirectUri, String code) {
        KakaoTokenResponse kakaoTokenResponse = kakaoAuthClient.kakaoAuth(clientId, redirectUri, code);
        return OAuthTokens.of(kakaoTokenResponse.access_token(), kakaoTokenResponse.id_token());
    }

    @Override
    public OAuthProfile getUserInfo(String accessToken) {
        return kakaoInfoClient.getUserInfo(BEARER + accessToken);
    }

    @Override
    public List<OIDCPublicKey> getPublicKeys() {
        return kakaoAuthClient.getKakaoOIDCOpenKeys().keys();
    }
}
