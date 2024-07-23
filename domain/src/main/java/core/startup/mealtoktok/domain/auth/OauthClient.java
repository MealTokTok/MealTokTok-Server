package core.startup.mealtoktok.domain.auth;

import java.util.List;

public interface OauthClient {

     OAuthUserInfo getUserInfo(String accessToken);

     OAuthToken auth(String clientId, String redirectUri, String code);

     List<OIDCPublicKey> getPublicKeys();

}
