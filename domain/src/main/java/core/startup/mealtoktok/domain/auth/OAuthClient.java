package core.startup.mealtoktok.domain.auth;

import static core.startup.mealtoktok.common.properties.OauthProperties.*;
import static core.startup.mealtoktok.common.properties.OauthProperties.REDIRECT_URL;

import java.util.List;

import core.startup.mealtoktok.domain.user.UserProfile;

public interface OAuthClient {

    UserProfile getUserProfile(String accessToken);

    OAuthTokens auth(String clientId, String redirectUri, String code);

    List<OIDCPublicKey> getPublicKeys();

    static String getKakaoOAuthUrl() {
        return BASE_URL + String.format(KAKAO_OAUTH_QUERY_STRING, CLIENT_ID, REDIRECT_URL);
    }
}
