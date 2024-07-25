package core.startup.mealtoktok.domain.auth;

import core.startup.mealtoktok.domain.user.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static core.startup.mealtoktok.common.properties.OauthProperties.*;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserReader userReader;
    private final OAuthClient oAuthClient;
    private final OAuthAuthenticator oAuthAuthenticator;
    private final TokenGenerator tokenGenerator;
    private final UserAppender userAppender;
    private final UserUpdater userUpdater;

    public boolean canRegistered(String idToken) {
        OAuthInfo oAuthInfo = oAuthAuthenticator.authenticate(idToken);
        return !userReader.isAlreadyRegistered(oAuthInfo);
    }

    public JwtTokens signUp(OAuthTokens oAuthTokens,
                            AddressWithCoordinate addressWithCoordinate) {
        OAuthInfo oAuthInfo = oAuthAuthenticator.authenticate(oAuthTokens.idToken());
        OAuthProfile oAuthProfile = oAuthClient.getUserInfo(oAuthTokens.accessToken());
        TargetUser targetUser = userAppender.append(oAuthInfo, UserInfo.of(oAuthProfile, addressWithCoordinate));
        return tokenGenerator.generate(targetUser);
    }

    public JwtTokens login(OAuthTokens oAuthTokens) {
        oAuthAuthenticator.authenticate(oAuthTokens.idToken());
        OAuthProfile oAuthProfile = oAuthClient.getUserInfo(oAuthTokens.accessToken());
        TargetUser targetUser = userUpdater.update(oAuthProfile);
        return tokenGenerator.generate(targetUser);
    }

    public String getKakaoLoginLink() {
        return BASE_URL +
                String.format(
                        KAKAO_OAUTH_QUERY_STRING,
                        CLIENT_ID,
                        REDIRECT_URL
                );
    }

    public JwtTokens getCredentialTest(String code) {
        OAuthTokens authToken = oAuthClient.auth(CLIENT_ID, REDIRECT_URL, code);
        JwtTokens jwtTokens = signUp(authToken, AddressWithCoordinate.of("충청북도 흥덕구 봉명동 2300-1", 36.629, 127.456));
        return jwtTokens;
    }


}
