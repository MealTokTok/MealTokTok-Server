package core.startup.mealtoktok.infra.auth.client;

import static core.startup.mealtoktok.common.consts.MealTokTokConstant.BEARER;

import java.util.List;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

import core.startup.mealtoktok.domain.auth.*;
import core.startup.mealtoktok.domain.user.UserProfile;
import core.startup.mealtoktok.infra.auth.dto.KakaoTokenResponse;

@Component
@RequiredArgsConstructor
public class KakaoClient implements OAuthClient {

    private final KakaoAuthClient kakaoAuthClient;
    private final KakaoInfoClient kakaoInfoClient;

    @Override
    public OAuthTokens auth(String clientId, String redirectUri, String code) {
        KakaoTokenResponse kakaoTokenResponse =
                kakaoAuthClient.kakaoAuth(clientId, redirectUri, code);
        return OAuthTokens.of(kakaoTokenResponse.access_token(), kakaoTokenResponse.id_token());
    }

    @Override
    public UserProfile getUserProfile(String accessToken) {
        return UserProfile.from(kakaoInfoClient.getUserInfo(BEARER + accessToken));
    }

    @Override
    public List<OIDCPublicKey> getPublicKeys() {
        return kakaoAuthClient.getKakaoOIDCOpenKeys().keys();
    }
}
