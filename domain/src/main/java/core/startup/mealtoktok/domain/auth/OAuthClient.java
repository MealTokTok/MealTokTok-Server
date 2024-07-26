package core.startup.mealtoktok.domain.auth;

import java.util.List;

import static core.startup.mealtoktok.common.properties.OauthProperties.*;
import static core.startup.mealtoktok.common.properties.OauthProperties.REDIRECT_URL;

public interface OAuthClient {

     OAuthProfile getUserInfo(String accessToken);

     OAuthTokens auth(String clientId, String redirectUri, String code);

     List<OIDCPublicKey> getPublicKeys();

     static String getKakaoOAuthUrl() {
         return  BASE_URL +
                 String.format(
                         KAKAO_OAUTH_QUERY_STRING,
                         CLIENT_ID,
                         REDIRECT_URL
                 );
     }

}
