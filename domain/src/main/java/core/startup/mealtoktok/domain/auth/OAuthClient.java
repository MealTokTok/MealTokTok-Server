package core.startup.mealtoktok.domain.auth;

import java.util.List;

public interface OAuthClient {

     OAuthProfile getUserInfo(String accessToken);

     OAuthTokens auth(String clientId, String redirectUri, String code);

     List<OIDCPublicKey> getPublicKeys();

}
