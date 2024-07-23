package core.startup.mealtoktok.domain.auth;

import core.startup.mealtoktok.domain.user.AddressWithCoordinate;
import core.startup.mealtoktok.domain.user.UserAppender;
import core.startup.mealtoktok.domain.user.UserInfo;
import core.startup.mealtoktok.domain.user.UserReader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static core.startup.mealtoktok.common.properties.OauthProperties.*;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserReader userReader;
    private final OauthClient oauthClient;
    private final OauthAuthenticator oauthAuthenticator;
    private final UserAppender userAppender;

    public boolean canRegistered(String oidcToken) {
        OAuthInfo oAuthInfo = oauthAuthenticator.authenticate(oidcToken);
        return !userReader.isAlreadyRegistered(oAuthInfo);
    }

    public void signUp(OAuthToken oAuthToken,
                       AddressWithCoordinate addressWithCoordinate) {
        OAuthInfo oAuthInfo = oauthAuthenticator.authenticate(oAuthToken.idToken());
        OAuthUserInfo oAuthUserInfo = oauthClient.getUserInfo(oAuthToken.accessToken());
        userAppender.append(oAuthInfo, UserInfo.of(oAuthUserInfo, addressWithCoordinate));
    }

    public String getKakaoLoginLink() {
        return BASE_URL +
                String.format(
                        KAKAO_OAUTH_QUERY_STRING,
                        CLIENT_ID,
                        REDIRECT_URL
                );
    }

    public void getCredentialTest(String code) {
        OAuthToken authToken = oauthClient.auth(CLIENT_ID, REDIRECT_URL, code);
        signUp(authToken, AddressWithCoordinate.of("충청북도 흥덕구 봉명동 2300-1", 36.629, 127.456));
    }

}
